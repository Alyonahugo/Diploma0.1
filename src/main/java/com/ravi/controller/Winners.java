package com.ravi.controller;

import com.ravi.spring.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.ravi.enumaration.StatusWinner;
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
    }

    private void check() {
        winnersName = calculator.getSchulze().getWinners();
        candadates =  calculator.getSchulze().getRegisterdCandidates();
        Integer countCanddates = candadates.size();
        if (countCanddates < 1){
            status = StatusWinner.NO_CADIDATES;
            LOG.info(status.toString());
            return;
        }

        if (!countCanddates.equals(winnersName.size())){
            status = StatusWinner.EXISTS_SIMILAR_WINNERS;
            update();
        }
        LOG.info(status.toString());
        loadProjectByName();
    }

    private void update() {
        if(winnersName == null){
            System.out.println("************");
            winnersName = new TreeSet<String>();
        }

        for (String candidate :  candadates){
            if (!winnersName.contains(candidate)){
                try{
                    winnersName.add(candidate);
                }catch (ArrayIndexOutOfBoundsException e){
                    status = StatusWinner.VOTES_NOT_EXIST;
                    LOG.info(status.toString());
                }
            }
        }

    }


    private void loadProjectByName() {
        winnersList = new ArrayList<Project>();
        List<Project> allProjects = orderListView.getProjects();
        for (String candidate :  winnersName) {
            for (Project project : allProjects){
                if (project.getName().equals(candidate)){
                    winnersList.add(project);
                    System.out.println("-----------------------");
                    System.out.println(candidate);
                }
            }
        }
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

}

