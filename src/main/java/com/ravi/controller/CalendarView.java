package com.ravi.controller;

import javax.faces.bean.ManagedBean;
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


    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
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
}
