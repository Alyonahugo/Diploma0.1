package com.ravi.spring.model;

import com.ravi.enumaration.Scope;
import com.ravi.enumaration.Sphere;
import com.ravi.enumaration.Status;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by User on 16.05.2015.
 */


@Entity
@Table(name = "project")
public class Project implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    private String displayName;

    @Basic(optional = false)
    @Column(name = "name")
    private String name;

    @Basic(optional = false)
    @Column(name = "meta")
    private String meta;

    @Basic(optional = false)
    @Column(name = "description")
    private String description;

    @Basic(optional = false)
    @Column(name = "sphere")
    private Sphere sphere;

    @Basic(optional = false)
    @Column(name = "manager")
    private String manager;


    private String team;
    private String author;
    private Status status;
    private Integer place;

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Sphere getSphere() {
        return sphere;
    }

    public void setSphere(Sphere sphere) {
        this.sphere = sphere;
    }


    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getPlace() {
        return place;
    }

    public void setPlace(Integer place) {
        this.place = place;
    }

    public Project() {}

    public Project(int id, String displayName, String name) {
        this.id = id;
        this.displayName = displayName;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public String showDetails(){
        return "Project{" +
                "id=" + id +
                ", displayName='" + displayName + '\'' +
                ", name='" + name + '\'' +
                ", meta='" + meta + '\'' +
                ", description='" + description + '\'' +
                ", sphere=" + sphere +
                ", manager='" + manager + '\'' +
                ", team='" + team + '\'' +
                ", author='" + author + '\'' +
                ", status=" + status +
                ", place=" + place +
                '}';
    }
}
