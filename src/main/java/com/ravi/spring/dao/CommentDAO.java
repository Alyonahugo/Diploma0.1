package com.ravi.spring.dao;

import com.ravi.spring.model.Comment;

import java.util.List;

/**
 * Created by User on 27.05.2015.
 */
public interface CommentDAO {

    public void addComment(Comment comment);

    public List<Comment> getComments();

    public void updateComment(Comment comment);

    public void updateComments(List<Comment> comments);



    List<Comment> getCommentsByTopicId(Integer selectedTopicId);
}