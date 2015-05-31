package com.ravi.spring.service;

import com.ravi.spring.model.Vote;

import java.util.List;

/**
 * Created by User on 25.05.2015.
 */
public interface VoteService {


    void addVote(Vote vote);


    void deleteVote(Vote vote);


    void updateVote(Vote vote);


    Vote getVoteById(int id);


    List<Vote> getVotes();

    boolean getVoteByEmpId(int emp_id);
}
