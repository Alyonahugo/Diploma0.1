package com.ravi.controller;


import com.ravi.enumaration.Status;
import com.ravi.spring.model.Employee;
import com.ravi.spring.model.Project;
import com.ravi.spring.service.ProjectService;
import org.primefaces.context.RequestContext;
import org.primefaces.event.*;
import org.primefaces.model.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 * Created by User on 21.05.2015.
 */

@ManagedBean(name="projectBean")
@SessionScoped
public class ProjectBean implements Serializable {

    private static Logger LOG = Logger.getLogger(ProjectBean.class.getName());


    private ResourceBundle rb = ResourceBundle.getBundle("data_settings", Locale.ENGLISH);

    private final int MAX_APPROVED_PROJECTS = 10;


    private List<Project> projects;
    private List<Project> approvedProjects;

    private Project project;
    private LazyDataModel<Project> lazyModel;

    private Project selectedProject;

    private boolean checkDate;
    private boolean before;
    private boolean after;


    @ManagedProperty(value="#{ProjectService}")
    @Autowired
    ProjectService projectService;

    @ManagedProperty(value="#{calendarView}")
    @Autowired
    CalendarView calendarView;



    public Project getProject() {
        saveUpdates();
        return project;
    }

    public List<Project> getProjects() {

        LOG.info("method get projects work");
        checkCountApProj();
        return projects;
    }

    private void checkCountApProj(){
        int countApprovedProjects = projectService.getApprovedProjects().size();
        if (countApprovedProjects >= MAX_APPROVED_PROJECTS){
            RequestContext requestContext = RequestContext.getCurrentInstance();

            requestContext.update("form:display");
            requestContext.execute("PF('dlg').show()");
        }
        else {
            projectService.updateProjectS(projects);
        }
    }

    public void setProjects(List<Project> projects) {


        LOG.info("method setproject work");
        this.projects = projects;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public LazyDataModel<Project> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<Project> lazyModel) {
        this.lazyModel = lazyModel;
    }

    public List<Project> getApprovedProjects() {
        approvedProjects = projectService.getApprovedProjects();
        return approvedProjects;
    }

    public void setApprovedProjects(List<Project> approvedProjects) {
        this.approvedProjects = approvedProjects;
    }

    public Project getSelectedProject() {
        if (selectedProject != null) {
            LOG.info("method get selected project work" + selectedProject.getName());
        }else
        System.out.println("selected project is empty");
        return selectedProject;
    }

    public void setSelectedProject(Project selectedProject)
    {

        LOG.info("method setSelected project work");
        this.selectedProject = selectedProject;
    }

    public boolean isCheckDate() {
        return checkDate;
    }

    public void setCheckDate(boolean checkDate) {
        this.checkDate = checkDate;
    }

    public void addProject(Project project){
       project.setStatus(Status.NOT_APPROVED);
        Employee emp = new Employee();
        emp.setId(1);
        project.setEmployee(emp);
       LOG.info(project.showDetails());
        projectService.addProject(project);
        projects = projectService.getProjects();

    }

    @PostConstruct
    public void init() {
        System.out.println("start");
        projects = projectService.getProjects();
        approvedProjects = projectService.getApprovedProjects();
        checkDate();
        System.out.println("finish");
    }

    private void checkDate(){
        LOG.info(" GET CURENT DATE " + calendarView.getDate1());
        LOG.info("resource bundle " + rb.getString("startRegDate"));

        Date currentDate = calendarView.getDate1();

        String startRegDateStr = rb.getString("startRegDate");
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date startRegDate = null;
        try {
            startRegDate = format.parse(startRegDateStr);
        } catch (ParseException e) {

        }

        String endRegDateStr = rb.getString("endRegDate");
        Date endRegDate = null;
        try {
            endRegDate = format.parse(endRegDateStr);
        } catch (ParseException e) {

        }

        if (currentDate.before(startRegDate)){
            before = true;
            after = false;
            checkDate  = false;

        }

        else {
              if (currentDate.before(endRegDate) || currentDate.equals(endRegDate)){
            before = false;
            after = false;
            checkDate = true;


              }
            else{

                after = true;
                checkDate = false;
                before = false;
            }
        }
        LOG.info("AFTER " + after + " befor " + before + " checkdate " + checkDate );

    }


    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Car Edited", new Integer(((Project) event.getObject()).getId()).toString());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", new Integer(((Project) event.getObject()).getId()).toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCellEdit(CellEditEvent event) {
        LOG.info("onCellEdit  work ");
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void saveUpdates(){
        LOG.info("projects were updated");
        for (Project project : projects){
            System.out.println(project.getMeta());
        }
    }

    private DashboardModel model;



    public void handleReorder(DashboardReorderEvent event) {
        FacesMessage message = new FacesMessage();
        message.setSeverity(FacesMessage.SEVERITY_INFO);
        message.setSummary("Reordered: " + event.getWidgetId());
        message.setDetail("Item index: " + (event.getItemIndex() + 1) + ", Column index: " + event.getColumnIndex() + ", Sender index: " + event.getSenderColumnIndex());

        addMessage(message);
    }

    public void handleClose(CloseEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Panel Closed", "Closed panel id:'" + event.getComponent().getId() + "'");

        addMessage(message);
    }

    public void handleToggle(ToggleEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, event.getComponent().getId() + " toggled", "Status:" + event.getVisibility().name());

        addMessage(message);
    }

    public void handleResize(ResizeEvent event) {
        UIComponent resizedUnit = event.getComponent(); //now get all the info related to resizedUnit
        System.out.println(" " + UIComponent.CURRENT_COMPONENT);
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public DashboardModel getModel() {
        return model;
    }

    public boolean isAfter() {
        return after;
    }

    public void setAfter(boolean after) {
        this.after = after;
    }

    public boolean isBefore() {
        checkDate();
        return before;
    }

    public void setBefore(boolean before) {
        this.before = before;
    }

    public void setModel(DashboardModel model) {
        this.model = model;
    }

    public CalendarView getCalendarView() {
        return calendarView;
    }

    public void setCalendarView(CalendarView calendarView) {
        this.calendarView = calendarView;
    }
}
