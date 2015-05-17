package com.ravi.controller;

/**
 * Created by User on 16.05.2015.
 */
import java.util.*;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.junit.Assert;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import schulze.Ballot;
import schulze.Schulze;
import schulze.calculator.Calculator;

import javax.faces.event.ActionEvent;


@ManagedBean

public class OrderListView {

    private static Logger LOG = Logger.getLogger(OrderListView.class.getName());


    @ManagedProperty("#{themeService}")
    private ThemeService service;

    private List<String> cities;
    private List<Project> projects;

@Autowired
@Qualifier("calculator")
    private Calculator calculator;
    static int count = 0;

    @PostConstruct
    public void init() {
        projects = new ArrayList<Project>();
        projects.add(new Project(10, "Cruze", "cruze"));
        projects.add(new Project(11, "Dark-Hive", "dark-hive"));
        projects.add(new Project(12, "Delta", "delta"));
        projects.add(new Project(13, "Dot-Luv", "dot-luv"));
        projects.add(new Project(14, "Eggplant", "eggplant"));
        projects.add(new Project(15, "Excite-Bike", "excite-bike"));
        projects.add(new Project(16, "Flick", "flick"));
        projects.add(new Project(17, "Glass-X", "glass-x"));

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

           resultOfVote.append(project.getDisplayName() + ",");
           resultOfVoteLog.append("" + i +" - " +  project.getDisplayName() + ", ");
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