package com.ravi.controller;

import com.ravi.enumaration.Status;
import com.ravi.spring.model.Car;
import com.ravi.spring.model.Project;
import com.ravi.spring.service.ProjectService;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by User on 29.05.2015.
 */


@ManagedBean(name="projectView")
@ViewScoped
public class ProjectView implements Serializable{

    private static Logger LOG = Logger.getLogger(ProjectView.class.getName());


    private List<Project> projects;


    private Project selectedProject;

    private boolean checkDate;
    private boolean before;
    private boolean after;


    @ManagedProperty(value="#{projectService}")
    @Autowired
    ProjectService projectService;

    @PostConstruct
    public void init(){
        projects = projectService.getProjects();
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public Project getSelectedProject() {
        System.out.println("selected projecy is not empty");
        return selectedProject;
    }

    public void setSelectedProject(Project selectedProject) {
        System.out.println("set selected project");
        this.selectedProject = selectedProject;
        switch (selectedProject.getStatus()) {
            case APPROVED: selectedProject.setStatus(Status.NOT_APPROVED); break;
            case  NOT_APPROVED: selectedProject.setStatus(Status.APPROVED); break;
        }
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Project updates", "Not project is " + selectedProject.getStatus());

        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }

    public ProjectService getProjectService() {
        return projectService;
    }

    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }


    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Car Edited", new Integer(((Project) event.getObject()).getId()).toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", new Integer(((Car) event.getObject()).getId()).toString());
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
    public void showWork(){
        LOG.info("showWork workimg");
    }
}
