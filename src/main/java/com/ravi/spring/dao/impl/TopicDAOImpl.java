package com.ravi.spring.dao.impl;

import com.ravi.spring.dao.TopicDAO;
import com.ravi.spring.model.Topic;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by User on 27.05.2015.
 */


@Repository
public class TopicDAOImpl implements TopicDAO {

    @Autowired
    private SessionFactory sessionFactory;



    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addTopic(Topic topic) {
        sessionFactory.getCurrentSession().save(topic);
    }

    @Override
    public List<Topic> getTopics() {
        return getSessionFactory().getCurrentSession()
                .createQuery("from Topic").list();
    }

    @Override
    public void updateTopic(Topic topic) {

    }

    @Override
    public void updateTopics(List<Topic> topics) {

    }


    @Override
    public List<Topic> getTopicsBySecId(int id) {
        return getSessionFactory().getCurrentSession()
                .createQuery("from Topic  where sec_id=?")
                .setParameter(0, id).list();
    }


}
