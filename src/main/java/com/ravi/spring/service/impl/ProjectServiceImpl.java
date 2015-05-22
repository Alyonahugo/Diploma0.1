package com.ravi.spring.service.impl;

import com.ravi.spring.dao.CustomerDAO;
import com.ravi.spring.dao.ProjectDAO;
import com.ravi.spring.model.Project;
import com.ravi.spring.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by User on 18.05.2015.
 */

@Service("projectService")
@Transactional
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectDAO projectDAO;

    @Override
    public void addProject(Project project) {
        projectDAO.addProject(project);

    }
}
