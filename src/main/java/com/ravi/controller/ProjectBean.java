package com.ravi.controller;

import com.ravi.spring.model.Project;
import com.ravi.spring.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.logging.Logger;

/**
 * Created by User on 21.05.2015.
 */

@ManagedBean(name="projectBean")
@RequestScoped
public class ProjectBean implements Serializable {

    private static Logger LOG = Logger.getLogger(ProjectBean.class.getName());

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    private Project project;


    @ManagedProperty(value="#{ProjectService}")
    @Autowired
    ProjectService projectService;

    public void addProject(Project project){
       LOG.info(project.showDetails());
        projectService.addProject(project);

    }

}
