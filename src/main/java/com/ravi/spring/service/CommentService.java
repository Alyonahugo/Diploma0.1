package com.ravi.spring.service;

import com.ravi.spring.model.Comment;
import com.ravi.spring.model.Project;

import java.util.List;

/**
 * Created by User on 27.05.2015.
 */
public interface CommentService {

    void addComment(Comment comment);

    List<Comment> getComments();

    void updateComment(Comment comment);

    void updateComments(List<Comment> comments);



    List<Comment> getCommentsByTopicId(Integer selectedTopicId);
}