package com.ravi.controller;

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

import javax.annotation.PostConstruct;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 24.05.2015.
 */
@ManagedBean
@SessionScoped
public class DashboardBacker{

public static final int DEFAULT_COLUMN_COUNT = 1;
private int columnCount = DEFAULT_COLUMN_COUNT;

private Dashboard dashboard;
    private  Application application;
    private   DashboardModel model;
    @Autowired
    private List<String> currentModel;

public DashboardBacker() {
        FacesContext fc = FacesContext.getCurrentInstance();
        application = fc.getApplication();

        dashboard = (Dashboard) application.createComponent(fc, "org.primefaces.component.Dashboard", "org.primefaces.component.DashboardRenderer");
        dashboard.setId("dashboard");

        model = new DefaultDashboardModel();

        for( int i = 0, n = getColumnCount(); i < n; i++ ) {
            DashboardColumn column = new DefaultDashboardColumn();
            model.addColumn(column);

        }
        dashboard.setModel(model);

        int items = 10;

        for( int i = 0, n = items; i < n; i++ ) {
            Panel panel = (Panel) application.createComponent(fc, "org.primefaces.component.Panel", "org.primefaces.component.PanelRenderer");
            panel.setId("measure_" + i);
            panel.setHeader("Dashboard Component " + i);
            panel.setClosable(true);
            panel.setToggleable(true);

            getDashboard().getChildren().add(panel);
            DashboardColumn column = model.getColumn(i%getColumnCount());
            column.addWidget(panel.getId());
            HtmlOutputText text = new HtmlOutputText();
            text.setValue("Dashboard widget bits!" );

            panel.getChildren().add(text);
        }
        }

public Dashboard getDashboard() {
        return dashboard;
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

    public void handleReorder(DashboardReorderEvent event) {
        FacesMessage message = new FacesMessage();
        message.setSeverity(FacesMessage.SEVERITY_INFO);
        message.setSummary("Reordered: " + event.getWidgetId());
        message.setDetail("Item index: " + (event.getItemIndex() + 1) + ", Column index: " + event.getColumnIndex() + ", Sender index: " + event.getSenderColumnIndex());

        addMessage(message);
    }

    public void handleClose(CloseEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Panel Closed", "Closed panel id:'" + event.getComponent().getId() + "'");

        addMessage(message);
    }

    public void handleToggle(ToggleEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, event.getComponent().getId() + " toggled", "Status:" + event.getVisibility().name());

        addMessage(message);
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void save(){
        int items = 10;
        System.out.println("i am working");
        for( int i = 0; i < items; i++ ) {


            System.out.println(" dashboard" + model.getColumn(0).getWidgets().get(i));


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