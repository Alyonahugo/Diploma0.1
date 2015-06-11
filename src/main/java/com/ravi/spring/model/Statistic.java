package com.ravi.spring.model;

import com.ravi.enumaration.Sphere;

import javax.persistence.*;

/**
 * Created by Olena_Polishchuk on 6/11/2015.
 */
public class Statistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stat_id")
    private int id;

    @Basic(optional = false)
    @Column(name = "sphere")
    private Sphere sphere;

    @Basic(optional = false)
    @Column(name = "persent")
    private String persent;

    @Basic(optional = false)
    @Column(name = "place")
    private Integer place;

    public Sphere getSphere() {
        return sphere;
    }

    public void setSphere(Sphere sphere) {
        this.sphere = sphere;
    }

    public String getPersent() {
        return persent;
    }

    public void setPersent(String persent) {
        this.persent = persent;
    }

    public Integer getPlace() {
        return place;
    }

    public void setPlace(Integer place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return "Statistic{" +
                "id=" + id +
                ", sphere=" + sphere +
                ", persent=" + persent +
                ", place=" + place +
                '}';
    }
}
