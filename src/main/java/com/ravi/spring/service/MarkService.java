package com.ravi.spring.service;

import com.ravi.spring.model.Mark;

import java.util.List;

/**
 * Created by User on 24.05.2015.
 */
public interface MarkService {

    public void addMark(Mark mark);

    public List<Mark> getMarks();

    public void updateMark(Mark mark);

    public void updateMarks(List<Mark> Marks);

    public List<Mark> getApprovedMarks();
}
