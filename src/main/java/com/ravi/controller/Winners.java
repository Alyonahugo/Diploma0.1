package com.ravi.controller;

import com.ravi.spring.model.Mark;
import com.ravi.spring.model.Project;
import com.ravi.spring.model.Vote;
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
import java.util.*;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Created by User on 17.05.2015.
 */

@ManagedBean
@SessionScoped
public class Winners {

private static Logger LOG = Logger.getLogger(OrderListView.class.getName());

    private Set<String> winnersName = new TreeSet<String>();

    private List<Project> winnersList;
    private List<String> candidates;

    private StatusWinner status;

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

    List<Project> listApprovedProjects;


    @PostConstruct
    public void init() {
        calculator.getSchulze().addAllCandidates(CreateListOfCandidates());
        setResultOfVotes();
        winnersList = new ArrayList<Project>();
        winnersName.addAll(calculator.getSchulze().getWinners());
        LOG.info("winners from schulze " + winnersName);
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
      /*  if (winnersName == null) {
            winnersName = calculator.getSchulze().getWinners();
        }
        candidates =  calculator.getSchulze().getRegisterdCandidates();
        Integer countCanddates = candidates.size();
        if (countCanddates < 1){
            status = StatusWinner.NO_CADIDATES;
            LOG.info(status.toString());
            return;
        }

        int count = 0;

        if(!countCanddates.equals(winnersName.size()) || count == 10){
            status = StatusWinner.EXISTS_SIMILAR_WINNERS;
            System.out.println("befor " + winnersName.toString());
            update();
            System.out.println("after " + winnersName.toString());
        }
        System.out.println(status +  " ----------- status ");
    //    LOG.info(status.toString());
       */
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


              //  try{


              /*  }catch (ArrayIndexOutOfBoundsException e){
                    status = StatusWinner.VOTES_NOT_EXIST;
                    LOG.info(status.toString());
                }*/



    }


    private void loadProjectByName() {
        listApprovedProjects = projectService.getApprovedProjects();
        winnersList = new ArrayList<Project>();
        LOG.info("winnars mnames at loadProjectByName() " + winnersName);

        for (String candidate :  winnersName) {
            LOG.info("winnars mnames at loadProjectByName().get(&) " + candidate);
            for (Project project : listApprovedProjects){
                if (project.getId() == new Integer(candidate)){
                    winnersList.add(project);
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
        check();
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


}

