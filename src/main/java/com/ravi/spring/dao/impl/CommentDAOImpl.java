package com.ravi.spring.dao.impl;

import com.ravi.spring.dao.CommentDAO;
import com.ravi.spring.model.Comment;
import com.ravi.spring.service.CommentService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by User on 27.05.2015.
 */
@Repository
public class CommentDAOImpl implements CommentDAO {
    @Autowired
    private SessionFactory sessionFactory;



    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }


    @Override
    public void addComment(Comment comment) {
        sessionFactory.getCurrentSession().save(comment);
        // .createQuery("from Project  where status=?")
        //            .setParameter(0, status).list();
    }

    @Override
    public List<Comment> getComments() {
        return sessionFactory.getCurrentSession().createQuery("from Comment").list();
    }

    @Override
    public void updateComment(Comment comment) {

    }

    @Override
    public void updateComments(List<Comment> comments) {

    }

    @Override
    public List<Comment> getCommentsByTopicId(Integer id) {
        return getSessionFactory().getCurrentSession()
                .createQuery("from Comment  where topic_id=?")
                .setParameter(0, id).list();
    }


}