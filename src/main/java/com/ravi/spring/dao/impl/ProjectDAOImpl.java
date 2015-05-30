package com.ravi.spring.dao.impl;


import com.ravi.enumaration.Status;
import com.ravi.spring.dao.ProjectDAO;
import com.ravi.spring.model.Project;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by User on 18.05.2015.
 */

@Repository
public class ProjectDAOImpl implements ProjectDAO {


    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addProject(Project project) {
        sessionFactory.getCurrentSession().save(project);
    }

    @Override
    public void deleteProject(Project project) {

    }

    @Override
    public void updateProject(Project project) {
        getSessionFactory().getCurrentSession().update(project);
    }

    @Override
    public Project getProjectById(int id) {
        return null;
    }

    @Override
    public List<Project> getProjects() {
        return sessionFactory.getCurrentSession().createQuery("from Project").list();
    }

    @Override
    public List<Project> getProjectsByStatus(Status status) {
        List<Project> list = getSessionFactory().getCurrentSession()
                .createQuery("from Project  where status=?")
                .setParameter(0, status).list();
        return list;
    }

    @Override
    public Project getProjectByName(String name) {
        List<Project> list = getSessionFactory().getCurrentSession()
                .createQuery("from Project  where name=?")
                .setParameter(0, name).list();
        return list.get(0);
    }


}
