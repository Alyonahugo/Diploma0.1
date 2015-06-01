package com.ravi.enumaration;

import javax.faces.bean.ManagedBean;
import java.io.Serializable;

/**
 * Created by User on 21.05.2015.
 */



public enum Sphere implements Serializable {


    Office_facilities("Office facilities"),
    Team_work("Team work"),
    Company_Values("Company Values"),
    Social_activity_within_Company("Social activity within Company"),
    Eco_friendliness("Eco-friendliness"),
    Other("Other");

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
