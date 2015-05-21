package com.ravi.enumaration;

import javax.faces.bean.ManagedBean;
import java.io.Serializable;

/**
 * Created by User on 21.05.2015.
 */



public enum Sphere implements Serializable {


    EDUCATION("EDUCATION"),
    SPORT("SPORT"),

    DANCE("DANCE"),SMART

    ("SMART"),

    REST("REST");

    private String name;



    public void setName(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }

    Sphere(String name) {
        this.name = name;
    }
}
