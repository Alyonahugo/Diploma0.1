package com.ravi.spring.service;

import com.ravi.spring.model.Topic;

import java.util.List;

/**
 * Created by User on 27.05.2015.
 */
public interface TopicService {

    void addTopic(Topic topic);

    List<Topic> getTopics();

    void updateTopic(Topic topic);

    void updateTopics(List<Topic> topics);

    List<Topic> getTopicsBySecId(int id);


}