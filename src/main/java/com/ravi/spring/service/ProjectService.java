package com.ravi.spring.service;

import com.ravi.spring.model.Project;

import java.util.List;

/**
 * Created by User on 18.05.2015.
 */
public interface ProjectService {

    public void addProject(Project project);

    public List<Project> getProjects();

    public void updateProject(Project project);

    public void updateProjectS(List<Project> projects);

    public List<Project> getApprovedProjects();

    public  List<Project> getProjectByName(String name);

    public void delete(Project project);

    List<Project> getProjectByEmpId(int emp_id);
}
