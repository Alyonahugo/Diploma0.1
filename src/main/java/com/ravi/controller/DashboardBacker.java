package com.ravi.controller;

import com.ravi.spring.model.Mark;
import com.ravi.spring.model.Project;
import com.ravi.spring.service.MarkService;
import com.ravi.spring.service.ProjectService;
import org.primefaces.component.dashboard.Dashboard;
import org.primefaces.component.panel.Panel;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.PostConstruct;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.*;

/**
 * Created by User on 24.05.2015.
 */
@ManagedBean
@ViewScoped
public  class DashboardBacker implements  Serializable{

public static final int DEFAULT_COLUMN_COUNT = 1;
private int columnCount = DEFAULT_COLUMN_COUNT;

    private static Dashboard dashboard;
    private  Application application;
    private  static DashboardModel model;
private int countOfPanels;


    @ManagedProperty(value="#{ProjectService}")
    @Autowired
    @Qualifier("projectService")
   ProjectService projectService;

    @ManagedProperty(value="#{MarkService}")
    @Autowired
    MarkService markService;

    private  Set<Project> approvedProj = new HashSet<Project>();
    private Map<String, Project> mapAppProj = new HashMap<String, Project>();
    private Set<Project> tempApprovedProjects = new HashSet<Project>();
    FacesContext fc;

public DashboardBacker() {
        }


public synchronized Dashboard getDashboard() {
        if(dashboard == null) {
            initDashBoard();
      }
        return dashboard;
        }

    private void updateApprovedList(List<Project> listProjects) {
        for (Project project : listProjects) {
            approvedProj.add(project);
        }

    }


    public void setDashboard(Dashboard dashboard) {
        this.dashboard = dashboard;
        }

public int getColumnCount() {
        return columnCount;
        }

public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
        }

    public ProjectService getProjectService() {
        return projectService;
    }

    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    public void handleReorder(DashboardReorderEvent event) {
        FacesMessage message = new FacesMessage();
        message.setSeverity(FacesMessage.SEVERITY_INFO);
        message.setSummary("Reordered: " + event.getWidgetId());
        message.setDetail("To " + (event.getItemIndex() + 1) + " position");

        addMessage(message);
    }


    public void handleToggle(ToggleEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, event.getComponent().getId() + " toggled", "Status:" + event.getVisibility().name());

        addMessage(message);
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    private synchronized void initDashBoard(){

        fc = FacesContext.getCurrentInstance();
        application = fc.getApplication();
     //   approvedProjects = projectService.getApprovedProjects();
        dashboard = (Dashboard) application.createComponent(fc, "org.primefaces.component.Dashboard", "org.primefaces.component.DashboardRenderer");
        dashboard.setId("dashboard");

        model = new DefaultDashboardModel();

        for( int i = 0, n = getColumnCount(); i < n; i++ ) {
            DashboardColumn column = new DefaultDashboardColumn();
            model.addColumn(column);
        }

        dashboard.setModel(model);
        approvedProj.addAll(projectService.getApprovedProjects());

        createPanels();
    }

    private void createMapAppProj(){
        for (Project project : approvedProj){
            mapAppProj.put("project_" + project.getName(), project);
        }
    }

    public synchronized void createPanels(){


        for ( int i = 0; i < countOfPanels; i ++){
            getDashboard().getChildren().remove(i);
        }
        countOfPanels = 0;
        List<UIComponent> newPanel = new ArrayList<UIComponent>();
        for(Project project :  approvedProj ) {
            Panel panel = (Panel) application.createComponent(fc, "org.primefaces.component.Panel", "org.primefaces.component.PanelRenderer");
            panel.setId("project_" + project.getName());
            panel.setHeader(project.getName());
            panel.setToggleable(true);

            getDashboard().getChildren().add(panel);
            DashboardColumn column = model.getColumn(0);
            column.addWidget(panel.getId());
            HtmlOutputText text = new HtmlOutputText();
            text.setValue(project.getDescription());
            countOfPanels++;
            panel.getChildren().add(text);
        }
    }

    public void save(){
        int items = approvedProj.size();
        createMapAppProj();
        System.out.println("i am working");
        for( int i = 0; i < items; i++ ) {
            Mark mark = new Mark(mapAppProj.get(model.getColumn(0).getWidgets().get(i)), i+1);


            System.out.println(" dashboard " + mark);
            markService.addMark(mark);


        }
    }
}

/*
@ManagedBean
@ViewScoped
public class DashboardBacker implements Serializable {

    private Dashboard dashboard;


    public DashboardBacker() {
    }

    @PostConstruct
    public void init() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Application application = fc.getApplication();

        dashboard = (Dashboard) application.createComponent(fc, "org.primefaces.component.Dashboard", "org.primefaces.component.DashboardRenderer");
        dashboard.setId("dashboard");

        DashboardModel model = new DefaultDashboardModel();
        DashboardColumn column1 = new DefaultDashboardColumn();
        column1.addWidget("sports");
        column1.addWidget("finance");
        model.addColumn(column1);
        dashboard.setModel(model);

        addChildren();
    }

    private void addChildren() {
        dashboard.getChildren().clear();
        //Loop though calling add for each panel you want to add to the dashboard.
        dashboard.getChildren().add( yourPanel );
    }

    public Dashboard getDashboard() {
        addChildren();
        return dashboard;
    }

    public void setDashboard(Dashboard dashboard) {
        this.dashboard = dashboard;
    }

    public void handleReorder(DashboardReorderEvent event) {
        //Display a message / save the dashboard state / do other stuff here
        addChildren();
    }
}*/