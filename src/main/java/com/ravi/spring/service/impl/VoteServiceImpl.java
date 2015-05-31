package com.ravi.spring.service.impl;

import com.ravi.spring.dao.ProjectDAO;
import com.ravi.spring.dao.VoteDAO;
import com.ravi.spring.model.Vote;
import com.ravi.spring.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.List;

/**
 * Created by User on 25.05.2015.
 */

@Service("voteService")
@Transactional
@ManagedBean(name = "voteService")
@ApplicationScoped
public class VoteServiceImpl implements VoteService {



    @Autowired
    VoteDAO voteDAO;

    public VoteDAO getVoteDAO() {
        return voteDAO;
    }

    public void setVoteDAO(VoteDAO voteDAO) {
        this.voteDAO = voteDAO;
    }


    @Override
    public void addVote(Vote vote) {
        voteDAO.addVote(vote);
    }

    @Override
    public void deleteVote(Vote vote) {

    }

    @Override
    public void updateVote(Vote vote) {

    }

    @Override
    public Vote getVoteById(int id) {
        return null;
    }

    @Override
    public List<Vote> getVotes() {
        return voteDAO.getVotes();
    }

    @Override
    public boolean getVoteByEmpId(int emp_id) {
        return voteDAO.getVoteByEmpId(emp_id);
    }


}
