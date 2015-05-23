package com.ravi.controller;

import com.ravi.lazy.LazyProjectDataModel;
import com.ravi.enumaration.Status;
import com.ravi.spring.model.Project;
import com.ravi.spring.service.ProjectService;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
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

    public void addProject(Project project){
       project.setStatus(Status.NOT_APPROVED);
       LOG.info(project.showDetails());
        projectService.addProject(project);
        init();

    }

    @PostConstruct
    public void init() {
        System.out.println("start");
        projects = projectService.getProjects();
        lazyModel = new LazyProjectDataModel(projects);
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
}
