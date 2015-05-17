package com.ravi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import schulze.StatusWinner;
import schulze.calculator.Calculator;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import java.util.*;
import java.util.logging.Logger;

/**
 * Created by User on 17.05.2015.
 */

@ManagedBean
public class Winners {

private static Logger LOG = Logger.getLogger(OrderListView.class.getName());

    private Set<String> winnersName;

    private List<Project> winnersList;
    private List<String> candadates;

    private StatusWinner status;

    @Autowired
    @Qualifier("calculator")
    private Calculator calculator;

    @Autowired
    private OrderListView orderListView;

    @PostConstruct
    public void init() {
        winnersList = new ArrayList<Project>();
        winnersName = calculator.getSchulze().getWinners();

        check();
    }

    private void check() {
        candadates =  calculator.getSchulze().getRegisterdCandidates();
        Integer countCanddates = candadates.size();
        if (countCanddates < 1){
            status = StatusWinner.NO_CADIDATES;
            return;
        }

        if (!countCanddates.equals(winnersName.size())){
            status = StatusWinner.EXISTS_SIMILAR_WINNERS;
            update();
        }
        loadProjectByName();
    }

    private void update() {
            winnersName = new LinkedHashSet<String>(10);

        for (String candidate :  candadates){
            if (!winnersName.contains(candidate)){
                winnersName.add(candidate);
            }
        }

    }


    private void loadProjectByName() {
        winnersList = new ArrayList<Project>();
        List<Project> allProjects = orderListView.getProjects();
        for (String candidate :  winnersName) {
            for (Project project : allProjects){
                if (project.getDisplayName().equals(candidate)){
                    winnersList.add(project);
                }
            }
        }
    }


    public List<Project> getWinnersList() {
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

}

