package com.ravi.spring.service.impl;

import com.ravi.spring.dao.MarkDAO;
import com.ravi.spring.dao.ProjectDAO;
import com.ravi.spring.model.Mark;
import com.ravi.spring.service.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.List;

/**
 * Created by User on 24.05.2015.
 */

@Service("markService")
@Transactional
@ManagedBean(name = "markService")
@ApplicationScoped
public class MarkServiceImpl implements MarkService {

    @Autowired
    MarkDAO markDAO;

    @Override
    public void addMark(Mark mark) {
        markDAO.addMark(mark);
    }

    @Override
    public List<Mark> getMarks() {
        return null;
    }

    @Override
    public void updateMark(Mark mark) {

    }

    @Override
    public void updateMarks(List<Mark> Marks) {

    }

    @Override
    public List<Mark> getApprovedMarks() {
        return null;
    }

    @Override
    public List<Integer> getMarksByProjectId(Integer progId) {
        return markDAO.getMarksByProjectId(progId);
    }
}
