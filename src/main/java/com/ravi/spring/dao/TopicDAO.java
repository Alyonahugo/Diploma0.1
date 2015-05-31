package com.ravi.spring.dao;

import com.ravi.spring.model.Section;
import com.ravi.spring.model.Topic;

import java.util.List;

/**
 * Created by User on 27.05.2015.
 */
public interface TopicDAO {


    public void addTopic(Topic topic);

    public List<Topic> getTopics();

    public void updateTopic(Topic topic);

    public void updateTopics(List<Topic> topics);


    List<Topic> getTopicsBySecId(int id);
}