package com.ravi.spring.dao.impl;

import com.ravi.spring.dao.VoteDAO;
import com.ravi.spring.model.Project;
import com.ravi.spring.model.Vote;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by User on 25.05.2015.
 */

@Repository
public class VoteDAOImpl implements VoteDAO{

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addVote(Vote vote) {
        sessionFactory.getCurrentSession().save(vote);
    }

    @Override
    public void deleteVote(Vote vote) {

    }

    @Override
    public void updateVote(Vote vote) {

    }

    @Override
    public Vote getVoteById(int id) {
        return null;
    }

    @Override
    public List<Vote> getVotes() {
        List<Vote> list = getSessionFactory().getCurrentSession()
                .createQuery("from Vote").list();
        return list;
    }

    @Override
    public boolean getVoteByEmpId(int emp_id) {
        List<Vote> list = getSessionFactory().getCurrentSession()
                .createQuery("from Vote where empp_id = ?").setParameter(0, emp_id).list();
       return list.size() == 1 ? true : false;

    }
}
