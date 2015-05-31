package com.ravi.controller;

import javax.faces.bean.SessionScoped;
import java.io.Serializable;

/**
 * Created by User on 31.05.2015.
 */
@SessionScoped
public class Event implements Serializable{
    private String name;
    private  String startDate;
    private String enddate;

    public Event(String name, String startDate, String enddate) {
        this.name = name;
        this.startDate = startDate;
        this.enddate = enddate;
    }

    public String getName() {
        System.out.println("sebd b=name " + name);
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", startDate='" + startDate + '\'' +
                ", enddate='" + enddate + '\'' +
                '}';
    }
}
