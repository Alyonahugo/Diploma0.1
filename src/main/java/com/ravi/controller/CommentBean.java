package com.ravi.controller;

import com.ravi.spring.model.Comment;
import com.ravi.spring.model.Employee;
import com.ravi.spring.model.Section;
import com.ravi.spring.model.Topic;
import com.ravi.spring.service.CommentService;
import com.ravi.spring.service.EmployeeService;
import com.ravi.spring.service.SectionService;
import com.ravi.spring.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.logging.Logger;

/**
 * Created by User on 27.05.2015.
 */
@ManagedBean(name="commentBean")

public class CommentBean implements Serializable {

    private static Logger LOG = Logger.getLogger(TopicBean.class.getName());


    @Autowired
    @ManagedProperty(value="#{commentService}")
    private CommentService commentService;

    @Autowired
    @ManagedProperty(value="#{sectionService}")
    private SectionService sectionService;

    @Autowired
    @ManagedProperty(value="#{topicService}")
    private TopicService topicService;

    @Autowired
    @ManagedProperty(value="#{employeeService}")
    private EmployeeService employeeService;

    private  String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public CommentService getCommentService() {
        return commentService;
    }

    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    public SectionService getSectionService() {
        return sectionService;
    }

    public void setSectionService(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    public TopicService getTopicService() {
        return topicService;
    }

    public void setTopicService(TopicService topicService) {
        this.topicService = topicService;
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public  void addComment( int topic){

        LOG.info("addComment method work");

        Comment comment = new Comment();

        Employee employee = new Employee();
        employee.setName("test");
        employeeService.addEmployee(employee);
        comment.setMessage(this.comment);
        comment.setEmployee(employee);
        Topic t = new Topic();
        t.setId(topic);
        comment.setTopic(t);
        comment.setEmployee(employee);
        commentService.addComment(comment);
        System.out.println("method add comment work" + comment);
    }
}
