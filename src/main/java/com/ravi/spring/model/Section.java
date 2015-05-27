package com.ravi.spring.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by User on 26.05.2015.
 */

@Entity
@Table(name = "section")
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sec_id")
    private int id;


    @Basic(optional = false)
    @Column(name = "name")

    private String name;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "section")
    private Set<Topic> topicRecords = new HashSet<Topic>(0);

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

    public Set<Topic> getTopicRecords() {
        return topicRecords;
    }

    public void setTopicRecords(Set<Topic> topicRecords) {
        this.topicRecords = topicRecords;
    }
}
