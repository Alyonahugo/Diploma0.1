package com.ravi.spring.dao;

import com.ravi.spring.model.Section;
import com.ravi.spring.model.Topic;

import java.util.List;

/**
 * Created by User on 27.05.2015.
 */
public interface TopicDAO {


    void addTopic(Topic topic);

    List<Topic> getTopics();

    void updateTopic(Topic topic);

    void updateTopics(List<Topic> topics);


    List<Topic> getTopicsBySecId(int id);
}