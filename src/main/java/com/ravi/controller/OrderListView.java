package com.ravi.controller;

/**
 * Created by User on 16.05.2015.
 */
import java.io.Serializable;
import java.util.*;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import com.ravi.spring.model.Forum;
import com.ravi.spring.model.Project;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import schulze.Ballot;
import schulze.Schulze;
import schulze.calculator.Calculator;

import javax.faces.event.ActionEvent;


@ManagedBean

public class OrderListView implements Serializable {

    private static Logger LOG = Logger.getLogger(OrderListView.class.getName());


    @ManagedProperty("#{themeService}")
    private ThemeService service;

    private List<String> cities;
    private List<Project> projects;

    public List<Forum> getForums() {
        return forums;
    }

    public void setForums(List<Forum> forums) {
        this.forums = forums;
    }

    private List<Forum> forums;

    private Project selectedProject;

@Autowired
@Qualifier("calculator")
    private Calculator calculator;
    static int count = 0;

    @PostConstruct
    public void init() {
        projects = new ArrayList<Project>();
        projects.add(new Project(10, "Cruze"));
        projects.add(new Project(11, "Dark-Hive"));
        projects.add(new Project(12, "Delta"));
        projects.add(new Project(13, "Dot-Luv"));
        projects.add(new Project(14, "Eggplant"));
        projects.add(new Project(15, "Excite-Bike"));
        projects.add(new Project(16, "Flick"));
        projects.add(new Project(17, "Glass-X"));

        forums= new ArrayList<Forum>();
        //sectionName, String author, Integer count
        forums.add(new Forum("Discution about English portal", "Olena_Polshchuk", 10));
        forums.add(new Forum("Bord games vs Kinect", "Olga_Kovalenko", 20));
        forums.add(new Forum("Dance lessons at company", "Kateryna_Madzara", 8));
        forums.add(new Forum("Interactive growth up", "Nazar_Sheremeta", 11));
        forums.add(new Forum("Bicycle for company", "Yaroslav_Mazay", 3));
        forums.add(new Forum("Online trening web site", "Mihayl_Lysevich", 3));
        forums.add(new Forum("Podorozhnyk not only for drivers", "Olga_Pletechuk", 3));
        forums.add(new Forum("Rest room for all employee", "Yuilia-Ahramovich", 33));

    }

    public ThemeService getService() {
        return service;
    }

    public void setService(ThemeService service) {
        this.service = service;
    }

    public List<String> getCities() {
        return cities;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public Project getSelectedProject() {
        return selectedProject;
    }

    public void setSelectedProject(Project selectedProject) {
        this.selectedProject = selectedProject;
    }


    public void onSelect(SelectEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Selected", event.getObject().toString()));
    }

    public void onUnselect(UnselectEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Unselected", event.getObject().toString()));
    }

    public void onReorder() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "List Reordered", null));
    }

    public void selectProjects(ActionEvent actionEvent) {
        StringBuilder resultOfVote = new StringBuilder();
        StringBuilder resultOfVoteLog = new StringBuilder();
        int i = 1;
       for (Project project : projects){

           resultOfVote.append(project.getName() + ",");
           resultOfVoteLog.append("" + i +" - " +  project.getName() + ", ");
           i++;
       }
        resultOfVote.deleteCharAt(resultOfVote.length()-1);

        LOG.info( "Result of one vote: " + resultOfVoteLog);

        putBallotsDevite(calculator.getSchulze(), resultOfVote.toString(), 1);
        count ++;
        if (count % 5 == 0) {

            System.out.println(calculator.getSchulze().getRegisterdCandidates() + "-------- schulze.getWinners() " + calculator.getSchulze().getWinners());
        }
    }

    private void putBallotsDevite(Schulze<String> schulze, String rankedList, int number) {

        String temp ;
        for (int i = 0; i < number; i++) {
            StringTokenizer st = new StringTokenizer(rankedList, ",");
            int rank = 1;
            final Ballot<String> ballot = new Ballot<String>();

            while(st.hasMoreTokens()){
                temp = st.nextToken();
                ballot.vote(temp, rank++);
                System.out.println(temp);
            }
            schulze.registerBallot(ballot);
        }
    }
}