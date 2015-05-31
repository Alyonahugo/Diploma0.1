package com.ravi.spring.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by User on 26.05.2015.
 */

@Entity
@Table(name = "topic")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topic_id")
    private int id;


    @Basic(optional = false)
    @Column(name = "name")

    private String name;
/*
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "topic")
    private Set<Comment> commentRecords = new HashSet<Comment>(0);
*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sec_id", nullable = false)
    private Section section;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
/*
    public Set<Comment> getCommentRecords() {
        return commentRecords;
    }

    public void setCommentRecords(Set<Comment> commentRecords) {
        this.commentRecords = commentRecords;
    }*/

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }


}