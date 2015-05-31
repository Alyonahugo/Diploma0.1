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
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.IOException;
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
@ViewScoped
public class ProjectBean implements Serializable {

    private static Logger LOG = Logger.getLogger(ProjectBean.class.getName());


    private ResourceBundle rb = ResourceBundle.getBundle("data_settings", Locale.ENGLISH);
    //TODO  get emp id from session
    private  int EMP_ID = 1;


    private List<Project> projects;
    private List<Project> approvedProjects;
    private List<Project> projectsForCurrentEmp;

    private Project project;
    private LazyDataModel<Project> lazyModel;

    private boolean checkDate;
    private boolean before;
    private boolean after;

    private boolean checkDateApp;
    private boolean beforeApp;
    private boolean afterApp;

    private boolean canEditMyProject;


    @ManagedProperty(value="#{ProjectService}")
    @Autowired
    ProjectService projectService;

    @ManagedProperty(value="#{calendarView}")
    @Autowired
    CalendarView calendarView;



    public Project getProject() {
        System.out.println(project);
        return project;
    }

    public List<Project> getProjects() {

       if (checkCountNewAppPro(projects) > 10){

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Already exists 10 approved projects", "Please decline one for approving new");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            LOG.info("exists 10 approved projects");
        }
        else {
            projectService.updateProjectS(projects);

        }
        projects = projectService.getProjects();
       return projects;
    }

    private int checkCountNewAppPro(List<Project> projects) {
        int count = 0;
        for(Project project : projects){
            if (project.getStatus().equals(Status.APPROVED)){
                count++;
            }
        }
        return count;
    }

    public void setProjects(List<Project> projects) {
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

    public boolean isCheckDate() {
        return checkDate;
    }

    public void setCheckDate(boolean checkDate) {
        this.checkDate = checkDate;
    }

    public boolean isBeforeApp() {
        checkAppDates();
        return beforeApp;
    }

    public void setBeforeApp(boolean beforeApp) {
        this.beforeApp = beforeApp;
    }

    public boolean isCheckDateApp() {
        return checkDateApp;
    }

    public void setCheckDateApp(boolean checkDateApp) {
        this.checkDateApp = checkDateApp;
    }

    public boolean isAfterApp() {
        return afterApp;
    }

    public void setAfterApp(boolean afterApp) {
        this.afterApp = afterApp;
    }

    public void addProjectTest() {

        //   if (checkOnlySpases(project)){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Data is not comlated", "Please input text");
        RequestContext.getCurrentInstance().showMessageInDialog(message);
        LOG.info("only space was input");
    }

    public void addProject(Project project){

        if (checkOnlySpases(project)){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Data is not comlated", "Please input text");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            LOG.info("only space was input");
        }
        else {
            if (!checkExistName(project)){
                project.setStatus(Status.NOT_APPROVED);
                Employee employee = new Employee();
                employee.setId(1);
                project.setEmployee(employee);
                LOG.info(project.showDetails());
                projectService.addProject(project);
                projects = projectService.getProjects();
            }
        }

    }

    private boolean checkExistName(Project project) {

        if(projectService.getProjectByName(project.getName()).size() < 1){
            return false;
        }
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "This name has already exist", "Please input another name");
        RequestContext.getCurrentInstance().showMessageInDialog(message);
        LOG.info("this name has already exist");
        return true;
    }

    private boolean checkOnlySpases(Project project) {
        if (project.getName().trim().length() == 0){
            return true;
        }
        if (project.getMeta().trim().length() == 0){
            return true;
        }
        if (project.getDescription().trim().length() == 0){
            return true;
        }
        return project.getManager().trim().length() == 0;
    }

    @PostConstruct
    public void init() {
        System.out.println("start");
        projects = projectService.getProjects();
        projectsForCurrentEmp = projectService.getProjectByEmpId(EMP_ID);
        approvedProjects = projectService.getApprovedProjects();
        checkDate();
        checkAppDates();
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
        LOG.info("AFTER " + after + " befor " + before + " checkdate " + checkDate);

    }

    private void checkAppDates() {
        Date currentDate = calendarView.getDate1();
        String startRegDateStr = rb.getString("startApprovedDate");
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date startRegDate = null;
        try {
            startRegDate = format.parse(startRegDateStr);
        } catch (ParseException e) {

        }

        String endRegDateStr = rb.getString("endApprovedDate");
        Date endRegDate = null;
        try {
            endRegDate = format.parse(endRegDateStr);
        } catch (ParseException e) {

        }

        if (currentDate.before(startRegDate)){
            beforeApp = true;
            afterApp = false;
            checkDateApp  = false;

        }

        else {
            if (currentDate.before(endRegDate) || currentDate.equals(endRegDate)){
                beforeApp = false;
                afterApp = false;
                checkDateApp = true;


            }
            else{

                afterApp = true;
                checkDateApp = false;
                beforeApp = false;
            }
        }

        LOG.info("AFTERApp  " + afterApp + " beforeApp " + beforeApp + " checkdateApp " + checkDateApp);
    }


    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Car Edited", new Integer(((Project) event.getObject()).getId()).toString());

        FacesContext.getCurrentInstance().addMessage(null, msg);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ec.redirect(ec.getRequestContextPath() + "/pages/myProjects.xhtml");
        } catch (IOException e) {
            LOG.warning("Can not redirect at on row edit");
        }
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", new Integer(((Project) event.getObject()).getId()).toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ec.redirect(ec.getRequestContextPath() + "/pages/myProjects.xhtml");
        } catch (IOException e) {
            LOG.warning("cant redirect after on row Cancel");
        }
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        projectService.updateProjectS(projectsForCurrentEmp);

        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void saveUpdates(ActionEvent actionEvent){
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

    public void delete(Project project){
        LOG.info("Project " + project + " was deleted");
        projectService.delete(project);
    }

    public List<Project> getProjectsForCurrentEmp() {
        if (projectsForCurrentEmp != null) {
            projectService.updateProjectS(projectsForCurrentEmp);
        }
        projectsForCurrentEmp = projectService.getProjectByEmpId(EMP_ID);
        return projectsForCurrentEmp;
    }

    public void setProjectsForCurrentEmp(List<Project> projectsForCurrentEmp) {
        this.projectsForCurrentEmp = projectsForCurrentEmp;
    }

    public boolean isCanEditMyProject() {
        checkCanEdit();
        return canEditMyProject;
    }

    private void checkCanEdit() {

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

        canEditMyProject = (currentDate.after(startRegDate) && currentDate.before(endRegDate)) || currentDate.equals(startRegDate) || currentDate.equals(endRegDate);
        LOG.info("canEditMyProject " + canEditMyProject);

    }

    public void setCanEditMyProject(boolean canEditMyProject) {
        this.canEditMyProject = canEditMyProject;
    }
}