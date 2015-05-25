package com.ravi.spring.model;

import javax.persistence.*;

/**
 * Created by User on 24.05.2015.
 */
@Entity
@Table(name = "mark")
public class Mark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Basic(optional = false)
    @Column(name = "mark")
    private int mark;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vote_id", nullable = false)
    private Vote vote;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "proj_id", nullable = false)
    private Project project;


    public Mark() {
    }

    public Mark(Project project, int i, Vote vote) {
        this.vote = vote;
        this.project = project;
        mark = i;
    }

    public Vote getVote() {
        return vote;
    }

    public void setVote(Vote vote) {
        this.vote = vote;
    }


    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public String toString() {
        return "Mark{" +
                "id=" + id +
                ", mark=" + mark +
                ", project=" + project +
                '}';
    }
}
