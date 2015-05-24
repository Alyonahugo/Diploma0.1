package com.ravi.controller;

import com.ravi.lazy.LazyProjectDataModel;
import com.ravi.enumaration.Status;
import com.ravi.spring.model.Project;
import com.ravi.spring.service.ProjectService;
import org.primefaces.component.layout.LayoutUnit;
import org.primefaces.context.RequestContext;
import org.primefaces.event.*;
import org.primefaces.model.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by User on 21.05.2015.
 */

@ManagedBean(name="projectBean")
@RequestScoped
public class ProjectBean implements Serializable {

    private static Logger LOG = Logger.getLogger(ProjectBean.class.getName());

    private List<Project> projects;
    private List<Project> approvedProjects;

    private Project project;
    private LazyDataModel<Project> lazyModel;


    @ManagedProperty(value="#{ProjectService}")
    @Autowired
    ProjectService projectService;

    public Project getProject() {
        System.out.println(project);
        return project;
    }

    public List<Project> getProjects() {

        for(Project pr : projects){
            System.out.println(pr.showDetails());
        }
        projectService.updateProjectS(projects);
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public LazyDataModel<Project> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<Project> lazyModel) {
        this.lazyModel = lazyModel;
    }

    public List<Project> getApprovedProjects() {
        approvedProjects = projectService.getApprovedProjects();
        return approvedProjects;
    }

    public void setApprovedProjects(List<Project> approvedProjects) {
        this.approvedProjects = approvedProjects;
    }

    public void addProject(Project project){
       project.setStatus(Status.NOT_APPROVED);
       LOG.info(project.showDetails());
        projectService.addProject(project);
        projects = projectService.getProjects();

    }

    @PostConstruct
    public void init() {
        System.out.println("start");
        projects = projectService.getProjects();
        approvedProjects = projectService.getApprovedProjects();

      //  lazyModel = new LazyProjectDataModel(projects);

        initDashboard();
        System.out.println("finish");
    }

    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Car Edited", new Integer(((Project) event.getObject()).getId()).toString());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", new Integer(((Project) event.getObject()).getId()).toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void saveUpdates(ActionEvent actionEvent){
        LOG.info("projects were updated");
        for (Project project : projects){
            System.out.println(project.getMeta());
        }
    }

    private DashboardModel model;


    public void initDashboard() {
        model = new DefaultDashboardModel();
        DashboardColumn column1 = new DefaultDashboardColumn();
        column1.addWidget("sports");
        column1.addWidget("finance");
        model.addColumn(column1);
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

    public void handleResize(ResizeEvent event) {
        UIComponent resizedUnit = event.getComponent(); //now get all the info related to resizedUnit
        System.out.println(" " +UIComponent.CURRENT_COMPONENT);
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public DashboardModel getModel() {
        return model;
    }
}
