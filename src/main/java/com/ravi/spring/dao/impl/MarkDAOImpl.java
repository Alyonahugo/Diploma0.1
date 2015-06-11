package com.ravi.spring.dao.impl;

import com.ravi.spring.dao.MarkDAO;
import com.ravi.spring.model.Mark;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by User on 24.05.2015.
 */

@Repository
public class MarkDAOImpl implements MarkDAO {


    @Autowired
    private SessionFactory sessionFactory;



    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addMark(Mark mark) {
        sessionFactory.getCurrentSession().save(mark);
    }

    @Override
    public List<Mark> getMarks() {
        return null;
    }

    @Override
    public void updateMark(Mark mark) {

    }

    @Override
    public void updateMarks(List<Mark> Marks) {

    }

    @Override
    public List<Mark> getApprovedMarks() {
        return null;
    }

    @Override
    public List<Integer> getMarksByProjectId(Integer progId) {
        return sessionFactory.getCurrentSession().createQuery("select mark from Mark  where proj_id=?")
                .setParameter(0, progId).list();
    }
}
