package com.ravi.controller;

/**
 * Created by User on 16.05.2015.
 */
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import javax.faces.event.ActionEvent;


@ManagedBean
public class OrderListView {

    @ManagedProperty("#{themeService}")
    private ThemeService service;

    private List<String> cities;
    private List<Project> projects;

    @PostConstruct
    public void init() {
        projects = new ArrayList<Project>();
        projects.add(new Project(10, "Cruze", "cruze"));
        projects.add(new Project(11, "Dark-Hive", "dark-hive"));
        projects.add(new Project(12, "Delta", "delta"));
        projects.add(new Project(13, "Dot-Luv", "dot-luv"));
        projects.add(new Project(14, "Eggplant", "eggplant"));
        projects.add(new Project(15, "Excite-Bike", "excite-bike"));
        projects.add(new Project(16, "Flick", "flick"));
        projects.add(new Project(17, "Glass-X", "glass-x"));

    }

    public ThemeService getService() {
        return service;
    }

    public void setService(ThemeService service) {
        this.service = service;
    }

    public List<String> getCities() {
        return cities;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }

    public List<Project> getProjects() {
        System.out.println("size " + projects.size());
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public void onSelect(SelectEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Selected", event.getObject().toString()));
    }

    public void onUnselect(UnselectEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Unselected", event.getObject().toString()));
    }

    public void onReorder() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "List Reordered", null));
    }

    public void selectProjects(ActionEvent actionEvent){
       // System.out.println(event.getObject());
        List<Project> list = new ArrayList<>();
     //   list.add((Project)event.getObject());
        System.out.println("-------- projects " + list.size());
    }
}