package com.ravi.controller;

import com.ravi.spring.model.Car;
import com.ravi.spring.service.impl.CarService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

/**
 * Created by User on 25.05.2015.
 */

@ManagedBean
@ViewScoped
public class ForumView implements Serializable {

    private List<Car> cars;

    @Autowired
    @ManagedProperty(value="#{carService}")
    private CarService carService;

    @PostConstruct
    public void init() {
        cars = carService.createCars(100);
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCarService(CarService service) {
        this.carService= service;
    }
}