package com.ravi.controller;

/**
 * Created by User on 21.05.2015.
 */

import com.ravi.enumaration.Sphere;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

@ManagedBean
@ViewScoped // or @RequestScoped
public class EnumValuesProvider implements Serializable {

    public EnumValuesProvider() {
    }

    public Sphere[] getSpheres(){
        return Sphere.values();
        }
}