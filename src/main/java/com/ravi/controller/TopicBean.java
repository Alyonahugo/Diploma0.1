package com.ravi.controller;

import com.ravi.spring.model.Section;
import com.ravi.spring.model.Topic;
import com.ravi.spring.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Created by User on 27.05.2015.
 */

@ManagedBean(name="topicBean")
@SessionScoped
public class TopicBean implements Serializable {


    private static Logger LOG = Logger.getLogger(TopicBean.class.getName());



    private List<Topic> topics;


    private Integer selectedSectionId;
    @Autowired
    private Topic selectedTopic;


    @ManagedProperty(value="#{topicService}")
    @Autowired
    private TopicService topicService;

    @PostConstruct
    public  void init(){

        Map map = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        selectedSectionId = new Integer(map.get("sectionId").toString());
        LOG.info("chose section - " + selectedSectionId);


    }

    public void addSection() throws IOException {

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(ec.getRequestContextPath() + "/pages/vote.xhtml");
        //    return "vote.xhtml";
    }



    public List<Topic> getTopics() {
        LOG.info("GET TOPICS FOR SECTION " + selectedSectionId);
        topics = topicService.getTopicsBySecId(selectedSectionId);
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }



    public TopicService getTopicService() {
        return topicService;
    }

    public void setTopicService(TopicService topicService) {
        this.topicService = topicService;
    }

    public Integer getSelectedSectionId() {
        return selectedSectionId;
    }

    public void setSelectedSectionId(Integer selectedSectionId) {
        this.selectedSectionId = selectedSectionId;
    }

    public Topic getSelectedTopic() {
        return selectedTopic;
    }

    public void setSelectedTopic(Topic selectedTopic) {
        this.selectedTopic = selectedTopic;
        System.out.println("selected topic " + this.selectedTopic.getName());


        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {

            ec.redirect(ec.getRequestContextPath() + "/pages/comment.xhtml" + "?topicId=" + selectedTopic.getId());
        } catch (IOException e) {

        }

    }
}
