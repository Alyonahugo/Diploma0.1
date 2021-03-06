package com.ravi.spring.service;

import com.ravi.spring.model.Topic;

import java.util.List;

/**
 * Created by User on 27.05.2015.
 */
public interface TopicService {

    public void addTopic(Topic topic);

    public List<Topic> getTopics();

    public void updateTopic(Topic topic);

    public void updateTopics(List<Topic> topics);

    public List<Topic> getTopicsBySecId(int id);


}
