package com.ravi.spring.service.impl;

import com.ravi.enumaration.Status;
import com.ravi.spring.dao.CustomerDAO;
import com.ravi.spring.dao.ProjectDAO;
import com.ravi.spring.model.Project;
import com.ravi.spring.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.List;

/**
 * Created by User on 18.05.2015.
 */

@Service("projectService")
@Transactional
@ManagedBean(name = "projectService")
@ApplicationScoped
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectDAO projectDAO;

    @Override
    public void addProject(Project project) {
        projectDAO.addProject(project);

    }

    @Override
    public List<Project> getProjects() {
        System.out.println("at service");
        return projectDAO.getProjects();
    }

    @Override
    public void updateProject(Project project) {
        projectDAO.updateProject(project);
    }

    @Override
    public void updateProjectS(List<Project> projects) {
        for(Project project : projects){
            projectDAO.updateProject(project);
        }
    }

    @Override
    public   synchronized  List<Project> getApprovedProjects() {

        return projectDAO.getProjectsByStatus(Status.APPROVED);
    }

    @Override
    public  List<Project> getProjectByName(String name) {
        return projectDAO.getProjectByName(name);
    }

}
