package com.ravi.spring.dao;


import com.ravi.enumaration.Status;
import com.ravi.spring.model.Project;

import java.util.List;

/**
 * Created by User on 18.05.2015.
 */
public interface ProjectDAO {

    public void addProject(Project project);


    public void deleteProject(Project project);


    public void updateProject(Project project);


    public Project getProjectById(int id);


    public List<Project> getProjects();

    public List<Project> getProjectsByStatus(Status status);

    Project getProjectByName(String name);
}
