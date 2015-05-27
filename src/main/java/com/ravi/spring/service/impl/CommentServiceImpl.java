package com.ravi.spring.service.impl;

import com.ravi.spring.dao.CommentDAO;
import com.ravi.spring.dao.ProjectDAO;
import com.ravi.spring.model.Comment;
import com.ravi.spring.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.List;

/**
 * Created by User on 27.05.2015.
 */

@Service("commentService")
@Transactional
@ManagedBean(name = "commentService")
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentDAO commentDAO;


    @Override
    public void addComment(Comment comment) {
        commentDAO.addComment(comment);

    }

    @Override
    public List<Comment> getComments() {
        return commentDAO.getComments();
    }

    @Override
    public void updateComment(Comment comment) {

    }

    @Override
    public void updateComments(List<Comment> comments) {

    }

    @Override
    public List<Comment> getApprovedComments() {
        return null;
    }
}
