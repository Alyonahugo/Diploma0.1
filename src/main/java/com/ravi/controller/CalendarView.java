package com.ravi.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 * Created by User on 28.05.2015.
 */

@ManagedBean
@SessionScoped
public class CalendarView {

    private Date date1;
    private String showDate;


    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }

    public void click() {
        RequestContext requestContext = RequestContext.getCurrentInstance();

        requestContext.update("form:display");
        requestContext.execute("PF('dlg').show()");
    }

    public Date getDate1() {
        System.out.println("send date " + date1);
        return date1;
    }

    public void setDate1(Date date1) {
        System.out.println("get date " + date1);
        this.date1 = date1;
    }

    public String getShowDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        showDate = dateFormat.format(date1);
        return showDate;
    }

    public void setShowDate(String showDate) {
        this.showDate = showDate;
    }

    @PostConstruct
    public void init(){

     /*   DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        //get current date time with Date()
        Date date = new Date();
        System.out.println(dateFormat.format(date));

        this.date1 = dateFormat.format(date);*/
        if (date1 == null){
            date1 = new Date();

        }
    }
}
