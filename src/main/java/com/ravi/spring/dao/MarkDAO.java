package com.ravi.spring.dao;

import com.ravi.spring.model.Mark;

import java.util.List;

/**
 * Created by User on 24.05.2015.
 */
public interface MarkDAO {

    void addMark(Mark mark);

    List<Mark> getMarks();

    void updateMark(Mark mark);

    void updateMarks(List<Mark> Marks);

    List<Mark> getApprovedMarks();
}
