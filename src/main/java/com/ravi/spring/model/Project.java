package com.ravi.spring.model;

import com.ravi.enumaration.Sphere;
import com.ravi.enumaration.Status;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by User on 16.05.2015.
 */


@Entity
@Table(name = "project")
public class Project implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "proj_id")
    private int id;


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

    @Basic(optional = false)
    @Column(name = "team")
    private String team;

    @Basic(optional = false)
    @Column(name = "autnor")
    private String author;

    @Basic(optional = false)
    @Column(name = "status")
    private Status status;

    private Integer place;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "project", cascade= {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<Mark> markRecords = new HashSet<Mark>(0);



    @ManyToOne(fetch = FetchType.EAGER, cascade= {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "empp_id", nullable = false)
    private Employee employee;


    public Set<Mark> getMarkRecords() {
        return markRecords;
    }

    public void setMarkRecords(Set<Mark> markRecords) {
        this.markRecords = markRecords;
    }


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

    public Project(int id, String name) {
        this.id = id;

        this.name = name;
    }

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


    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return name;
    }

    public String showDetails(){
        return "Project{" +
                "id=" + id +
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Project project = (Project) o;

        if (id != project.id) return false;
        if (name != null ? !name.equals(project.name) : project.name != null) return false;
        if (meta != null ? !meta.equals(project.meta) : project.meta != null) return false;
        if (description != null ? !description.equals(project.description) : project.description != null) return false;
        if (sphere != project.sphere) return false;
        if (manager != null ? !manager.equals(project.manager) : project.manager != null) return false;
        if (team != null ? !team.equals(project.team) : project.team != null) return false;
        if (author != null ? !author.equals(project.author) : project.author != null) return false;
        if (status != project.status) return false;
        return !(place != null ? !place.equals(project.place) : project.place != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (meta != null ? meta.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (sphere != null ? sphere.hashCode() : 0);
        result = 31 * result + (manager != null ? manager.hashCode() : 0);
        result = 31 * result + (team != null ? team.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (place != null ? place.hashCode() : 0);
        return result;
    }
}