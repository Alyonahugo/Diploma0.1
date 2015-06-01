package com.ravi.controller;

import com.ravi.spring.model.*;
import com.ravi.spring.service.CommentService;
import com.ravi.spring.service.SectionService;
import com.ravi.spring.service.TopicService;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Logger;

/**
 * Created by User on 27.05.2015.
 */
@ManagedBean(name="commentBean")

public class CommentBean implements Serializable {

    private static Logger LOG = Logger.getLogger(CommentBean.class.getName());


    @Autowired
    @ManagedProperty(value="#{commentService}")
    private CommentService commentService;

    @Autowired
    @ManagedProperty(value="#{sectionService}")
    private SectionService sectionService;

    @Autowired
    @ManagedProperty(value="#{topicService}")
    private TopicService topicService;
/*
    @Autowired
    @ManagedProperty(value="#{employeeService}")
    private EmployeeService employeeService;
*/
    private  String comment;
    private String topicName;
    //TODO - get emp id from session
    private int EMP_ID = 1;

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
/*
    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
*/
    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public  void addComment( int topic){


        if (comment.trim().length() == 0){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Data is not comlated", "Please input text");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            LOG.info("only space was input into comment");
        }else {

            LOG.info("addComment method work");


            Employee employee = new Employee();
            employee.setId(EMP_ID);
            Topic t = new Topic();
            t.setId(topic);
            setCommentIntoDB(t, employee);
            System.out.println("method add comment work" + comment);

            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

            try {
                ec.redirect(ec.getRequestContextPath() + "/pages/comment.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }




    }

    public  void addTopic( int secId){

        if (checkOnlySpases()){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Data is not comlated", "Please input text");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            LOG.info("only space was input into comment");
        }else {

            Employee employee = new Employee();
            employee.setId(EMP_ID);
            //   employeeService.addEmployee(employee);

            Section sec = new Section();
            sec.setId(secId);

            Topic t = new Topic();
            t.setName(topicName);
            t.setSection(sec);
            topicService.addTopic(t);
            setCommentIntoDB(t, employee);
            LOG.info("method addTopic work" + topicName + " - " + comment);


            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

            try {
                ec.redirect(ec.getRequestContextPath() + "/pages/topic.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private boolean checkOnlySpases() {
        if (comment.trim().length() == 0){
            return true;
        }
        return topicName.trim().length() == 0;
    }


    private void setCommentIntoDB(Topic topic, Employee employee){
        Comment comment = new Comment();
        comment.setMessage(this.comment);
        comment.setEmployee(employee);
        comment.setTopic(topic);
        comment.setEmployee(employee);
        commentService.addComment(comment);
    }
}