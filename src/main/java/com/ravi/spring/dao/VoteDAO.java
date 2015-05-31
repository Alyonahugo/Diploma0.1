package com.ravi.spring.dao;

import com.ravi.enumaration.Status;
import com.ravi.spring.model.Project;
import com.ravi.spring.model.Vote;

import java.util.List;

/**
 * Created by User on 25.05.2015.
 */
public interface VoteDAO {

    void addVote(Vote vote);


    void deleteVote(Vote vote);


    void updateVote(Vote vote);


    Vote getVoteById(int id);


    List<Vote> getVotes();


    boolean getVoteByEmpId(int emp_id);
}
