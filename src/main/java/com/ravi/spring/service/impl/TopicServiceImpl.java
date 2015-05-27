package com.ravi.spring.service.impl;

import com.ravi.spring.dao.TopicDAO;
import com.ravi.spring.model.Topic;
import com.ravi.spring.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.List;

/**
 * Created by User on 27.05.2015.
 */

@Service("topicService")
@Transactional
@ManagedBean(name = "topicService")
@ApplicationScoped
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicDAO topicDAO;

    public TopicDAO getTopicDAO() {
        return topicDAO;
    }

    public void setTopicDAO(TopicDAO topicDAO) {
        this.topicDAO = topicDAO;
    }

    @Override
    public void addTopic(Topic topic) {
        topicDAO.addTopic(topic);
    }

    @Override
    public List<Topic> getTopics() {
        return null;
    }

    @Override
    public void updateTopic(Topic topic) {

    }

    @Override
    public void updateTopics(List<Topic> topics) {

    }

    @Override
    public List<Topic> getApprovedTopics() {
        return null;
    }
}
