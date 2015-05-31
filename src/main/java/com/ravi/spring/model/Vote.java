package com.ravi.spring.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by User on 25.05.2015.
 */

@Entity
@Table(name = "vote")
public class Vote implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vote_id")
    private int id;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "vote")
    private Set<Mark> marksRecords = new LinkedHashSet<Mark>(0);


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "empp_id", nullable = false)
    private Employee employee;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Set<Mark> getMarksRecords() {
        return marksRecords;
    }

    public void setMarksRecords(Set<Mark> marksRecords) {
        this.marksRecords = marksRecords;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}