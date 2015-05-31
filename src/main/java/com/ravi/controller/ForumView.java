package com.ravi.controller;


import com.ravi.spring.model.Comment;
import com.ravi.spring.model.Topic;
import com.ravi.spring.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by User on 25.05.2015.
 * Created by User on 25.05.2015.
 */

@ManagedBean
@SessionScoped
public class ForumView implements Serializable {

    private static Logger LOG = Logger.getLogger(ForumView.class.getName());



    private List<Comment> comments;
    private Integer selectedTopicId;

    @Autowired
    @ManagedProperty(value="#{commentService}")
    private CommentService commentService;


    //  @PostConstruct
    public void init() {

        Map map = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        if (map.get("topicId") != null){
            selectedTopicId = new Integer(map.get("topicId").toString());
        }
        LOG.info("chose section - " + selectedTopicId);


    }

    public List<Comment> getComments() {
        init();
        LOG.info("GET TOPICS FOR topic with id - " + selectedTopicId);
        comments = commentService.getCommentsByTopicId(selectedTopicId);
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public CommentService getCommentService() {
        return commentService;
    }

    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    public Integer getSelectedTopicId() {
        return selectedTopicId;
    }

    public void setSelectedTopicId(Integer selectedTopicId) {
        this.selectedTopicId = selectedTopicId;
    }
}