package com.ravi.spring.dao;


import com.ravi.enumaration.Status;
import com.ravi.spring.model.Project;

import java.util.List;

/**
 * Created by User on 18.05.2015.
 */
public interface ProjectDAO {

    void addProject(Project project);


    void deleteProject(Project project);


    void updateProject(Project project);


    Project getProjectById(int id);


    List<Project> getProjects();

    List<Project> getProjectsByStatus(Status status);

    List<Project> getProjectByName(String name);

    List<Project> getProjectByEmpId(int emp_id);
}
