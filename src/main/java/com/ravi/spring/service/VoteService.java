package com.ravi.spring.service;

import com.ravi.spring.model.Vote;

import java.util.List;

/**
 * Created by User on 25.05.2015.
 */
public interface VoteService {


    public void addVote(Vote vote);


    public void deleteVote(Vote vote);


    public void updateVote(Vote vote);


    public Vote getVoteById(int id);


    public List<Vote> getVotes();

    boolean getVoteByEmpId(int emp_id);
}
