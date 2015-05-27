package com.ravi.controller;

import com.ravi.spring.model.Car;
import com.ravi.spring.model.Comment;
import com.ravi.spring.service.CommentService;
import com.ravi.spring.service.impl.CarService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

/**
 * Created by User on 25.05.2015.
 */

@ManagedBean
@ViewScoped
public class ForumView implements Serializable {

    private List<Comment> comments;

    @Autowired
    @ManagedProperty(value="#{commentService}")
    private CommentService commentService;


  //  @PostConstruct
    public void init() {
        comments = commentService.getComments();
    }

    public List<Comment> getComments() {
        init();
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
}