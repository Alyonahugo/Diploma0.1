package com.ravi.spring.service;

import com.ravi.spring.model.Project;

import java.util.List;

/**
 * Created by User on 18.05.2015.
 */
public interface ProjectService {

    void addProject(Project project);

    List<Project> getProjects();

    void updateProject(Project project);

    void updateProjectS(List<Project> projects);

    List<Project> getApprovedProjects();

    List<Project> getProjectByName(String name);

    void delete(Project project);

    List<Project> getProjectByEmpId(int emp_id);
}
