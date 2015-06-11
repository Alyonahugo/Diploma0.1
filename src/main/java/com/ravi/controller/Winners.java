package com.ravi.controller;

import com.ravi.enumaration.Sphere;
import com.ravi.spring.model.Mark;
import com.ravi.spring.model.Project;
import com.ravi.spring.model.Statistic;
import com.ravi.spring.model.Vote;
import com.ravi.spring.service.MarkService;
import com.ravi.spring.service.ProjectService;
import com.ravi.spring.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.ravi.enumaration.StatusWinner;
import schulze.calculator.Calculator;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Created by User on 17.05.2015.
 */

@ManagedBean
@SessionScoped
public class Winners implements Serializable {

private static Logger LOG = Logger.getLogger(OrderListView.class.getName());

    private ResourceBundle rb = ResourceBundle.getBundle("data_settings", Locale.ENGLISH);

    private Set<String> winnersName = new LinkedHashSet<String>();


    private List<Project> winnersList;
    private List<String> candidates;
    private Map<Integer, Project> statMap;

    private StatusWinner status;
    private boolean showResult;
    private int[][] array;
    private double[][] perArray;


    @Autowired
    @Qualifier("calculator")
    private Calculator calculator;

    @Autowired
    private OrderListView orderListView;


    @ManagedProperty(value="#{ProjectService}")
    @Autowired
    ProjectService projectService;

    @ManagedProperty(value="#{VoteService}")
    @Autowired
    VoteService voteService;

    @ManagedProperty(value="#{calendarView}")
    @Autowired
    CalendarView calendarView;

    @ManagedProperty(value="#{markService}")
    @Autowired
    MarkService markService;


    List<Project> listApprovedProjects;
    List<Statistic> statistic;


  //  @PostConstruct
    public void loadWinners() {
        calculator.getSchulze().addAllCandidates(CreateListOfCandidates());
        setResultOfVotes();
        winnersList = new ArrayList<Project>();
        winnersName.addAll(calculator.getSchulze().getWinners());
        LOG.info("winners from schulze " + winnersName);
        check();
        LOG.info("winners after updating " + winnersName);
        projectService.updateProjectS(winnersList);
        createStatistic();
    }

    private void setResultOfVotes() {
        List<Vote> voteList = voteService.getVotes();
        for (Vote vote : voteList){
            setVotes(createMapByVoteMarks(vote));
        }
    }

    private List<Project> createMapByVoteMarks(Vote vote) {
        Map<Integer, Project> map = new TreeMap<Integer, Project>();
        Set<Mark> listMarks = vote.getMarksRecords();
        for (Mark mark : listMarks){
            map.put(mark.getMark(), mark.getProject());
        }
        LOG.info("ONE VOTE " + map.values());
        return new ArrayList<Project>(map.values());
    }

    private List<String> CreateListOfCandidates() {
        listApprovedProjects = projectService.getApprovedProjects();
        List<String> listIdCandidates = new ArrayList<String>();
        for (Project project : listApprovedProjects){
            listIdCandidates.add("" + project.getId());
        }
        LOG.info("candidates " + listIdCandidates);
        candidates = listIdCandidates;
        return listIdCandidates;
    }

    private void check() {

        if (candidates.size() != winnersName.size()){
            LOG.warning("Exists similar winners. candidates.size -  " + candidates.size() + " and winnersName.size() " + winnersName.size());
            winnersName.addAll(candidates);
            LOG.warning("After adding. candidates.size -  " + candidates.size() + " and winnersName.size() " + winnersName.size());

        }
        loadProjectByName();
    }

    private void update() {
        if(winnersName == null){
            winnersName = new TreeSet<String>();
        }

        Set<String> set = new TreeSet<String>();
        set.addAll(winnersName);
        set.addAll(candidates);
        winnersName = set;
    }


    private void loadProjectByName() {
        listApprovedProjects = projectService.getApprovedProjects();
        winnersList = new ArrayList<Project>();
        LOG.info("winnars mnames at loadProjectByName() " + winnersName);
        int place = 1;
        for (String candidate :  winnersName) {
            LOG.info("winnars mnames at loadProjectByName().get(&) " + candidate);
            for (Project project : listApprovedProjects){
                if (project.getId() == new Integer(candidate)){
                    project.setPlace(place);
                    winnersList.add(project);
                    place++;
                }
            }
        }
    }

    private void setVotes(List<Project> listProjects){
        StringBuilder resultOfVote = new StringBuilder();
        StringBuilder resultOfVoteLog = new StringBuilder();
        int i = 1;
       for (Project project : listProjects){
            resultOfVote.append(project.getId() + ",");
            resultOfVoteLog.append("" + i +" - " +  project.getName() + ", ");
            i++;
        }
        if (!(resultOfVote.length() < 1)) {
            resultOfVote.deleteCharAt( resultOfVote.length() - 1);
        }

        LOG.info( "Result of one vote: " + resultOfVote);

        calculator.putBallotsDevite(calculator.getSchulze(), resultOfVote.toString(), 1);
    }


    public List<Project> getWinnersList() {
        loadWinners();
        return winnersList;
    }

    public void setWinnersList(List<Project> winnersList) {
        this.winnersList = winnersList;
    }

    public StatusWinner getStatus() {
        return status;
    }

    public void setStatus(StatusWinner status) {
        this.status = status;
    }


    public ProjectService getProjectService() {
        return projectService;
    }

    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }


    public VoteService getVoteService() {
        return voteService;
    }

    public void setVoteService(VoteService voteService) {
        this.voteService = voteService;
    }

    public List<Statistic> getStatistic() {
        return statistic;
    }

    public void setStatistic(List<Statistic> statistic) {
        this.statistic = statistic;
    }

    public boolean isShowResult() {
        checkDate();
        return showResult;
    }

    private void checkDate() {
        LOG.info(" GET CURENT DATE " + calendarView.getDate1());
        LOG.info("resource bundle " + rb.getString("startResultDate"));

        Date currentDate = calendarView.getDate1();

        String startRegDateStr = rb.getString("startResultDate");
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date startResShowDate = null;
        try {
            startResShowDate = format.parse(startRegDateStr);
        } catch (ParseException e) {

        }


        showResult = !currentDate.before(startResShowDate);

        LOG.info("showResult " + showResult);

    }

    public void setShowResult(boolean showResult) {
        this.showResult = showResult;
    }

    public CalendarView getCalendarView() {
        return calendarView;
    }

    public void setCalendarView(CalendarView calendarView) {
        this.calendarView = calendarView;
    }

    private void createStatistic(){
        createMapForStatictic();
        createCountVotes();
        createPersonTable();
        statistic = new ArrayList<Statistic>();
        createCandObj();

    }


    private void createMapForStatictic(){
        statMap = new TreeMap<Integer, Project>();
        for (Project project : winnersList){
            statMap.put(project.getId(), project);
        }
    }

    private void createCountVotes(){
        List<Integer> marks;
        int j = -1;
        array = new int[winnersList.size()][winnersList.size()];
        for (Integer progId : statMap.keySet()){
            j++;

            marks = markService.getMarksByProjectId(progId);
            for (Integer mark : marks){
                for (int i = 0; i < winnersList.size(); i++){
                    if ((mark-1) == i){
                        System.out.println(progId);
                        System.out.println(j + "-" + i);
                        array[j][i] += 1;
                    }
                }
            }
        }

    }

    private void createPersonTable(){
        perArray = new double[winnersList.size()][winnersList.size()];
        for (int j = 0; j < winnersList.size(); j++){
            for (int i = 0; i < winnersList.size(); i++){
                perArray[j][i] = (double)((double)array[j][i] * 100.0)/winnersList.size();
            }
            System.out.println();
        }

    }

    private void createCandObj(){
        int j = -1;
        for (Project project : statMap.values()){
            j ++;
            for (int i = 0; i < winnersList.size(); i++){
                Statistic st = new Statistic();
                st.setSphere(project.getSphere());
                DecimalFormat dt = new DecimalFormat("#.##");
                st.setPersent(dt.format(perArray[j][i]));
                st.setPlace(i + 1);
                statistic.add(st);
            }
        }
        showStatList();
    }

    private void showStatList() {
        for (Statistic stat : statistic){
            System.out.println(stat);
        }
    }

    private void show(){

        for (int j = 0; j < winnersList.size(); j++){
            for (int i = 0; i < winnersList.size(); i++){
                System.out.print(perArray[j][i] + " ");
            }
            System.out.println();
        }
    }
}

