package com.ravi.spring.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by User on 26.05.2015.
 */

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empp_id")
    private int id;


    @Basic(optional = false)
    @Column(name = "name")
    private String name;

   @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
    private Set<Comment> commentRecords = new HashSet<Comment>(0);

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
    private Set<Project> projectSet = new LinkedHashSet<Project>(0);

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

    public Set<Comment> getCommentRecords() {
        return commentRecords;
    }

    public void setCommentRecords(Set<Comment> commentRecords) {
        this.commentRecords = commentRecords;
    }


    public Set<Project> getProjectSet() {
        return projectSet;
    }

    public void setProjectSet(Set<Project> projectSet) {
        this.projectSet = projectSet;
    }

}
