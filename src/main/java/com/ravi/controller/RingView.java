package com.ravi.controller;

/**
 * Created by User on 31.05.2015.
 */
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;


@ManagedBean
@SessionScoped
public class RingView implements Serializable {


    private static Logger LOG = Logger.getLogger(RingView.class.getName());
    private ResourceBundle rb = ResourceBundle.getBundle("data_settings", Locale.ENGLISH);


    private List<Event> events;
    private Event selectedEvent;
    private String name;
    private String startDate;
    private String endDate;

    @PostConstruct
    public void init() {
        events = new ArrayList<Event>();
        Event eventReg = new Event("Registration", rb.getString("startRegDate"), rb.getString("endRegDate"));
        events.add(eventReg);
        selectedEvent = eventReg;
        Event eventApp = new Event("Approving", rb.getString("startApprovedDate"), rb.getString("endApprovedDate"));
        events.add(eventApp);

        Event eventVot = new Event("Voting", rb.getString("startVotingDate"), rb.getString("endVotingDate"));
        events.add(eventVot);

        Event eventRes = new Event("Result", rb.getString("startResultDate"), "");
        events.add(eventRes);

        Event eventMP = new Event("My projects edit", rb.getString("startRegDate"), rb.getString("endRegDate"));
        events.add(eventMP);

    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {

        this.events = events;
    }

    public Event getSelectedEvent() {
        System.out.println("get selected event " + selectedEvent);
        return selectedEvent;
    }

    public void setSelectedEvent(Event selectedEvent) {
        System.out.println("set selected event" + selectedEvent);
        name = selectedEvent.getName();
        startDate = selectedEvent.getStartDate();
        endDate = selectedEvent.getEnddate();
        this.selectedEvent = selectedEvent;
    }

    public String getName() {
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

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void chooseCar() {
        RequestContext.getCurrentInstance().openDialog("carDialog");
    }

    public void onCarChosen(SelectEvent event) {
        Event car = (Event) event.getObject();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Car Selected", "Id:" + car.getName());

        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}