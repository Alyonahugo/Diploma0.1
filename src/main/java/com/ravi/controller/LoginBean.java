package com.ravi.controller;
import com.ravi.Utils.Util;
import com.ravi.spring.model.Employee;
import com.ravi.spring.service.CommentService;
import com.ravi.spring.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;


/**
 * Created by User on 01.06.2015.
 */

/**
 *
 * @author User
 */
@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {
    private static Logger LOG = Logger.getLogger(LoginBean.class.getName());


    @Autowired
    @ManagedProperty(value="#{employeeService}")
    private EmployeeService employeeService;


    private static final long serialVersionUID = 1L;
    private String password;
    private String message, uname;
    private Employee employee;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public void loginProject(ActionEvent event) {
        boolean result;
        LOG.info("try logind with login " + uname  + " and password " + password);
        employee = employeeService.getEmployeeByLoginPassword(uname, password);
                if ( employee == null){
                    result = false;}
        else{
            result = true;
        }

        if (result) {
            // get Http Session and store username
            HttpSession session = Util.getSession();
            session.setAttribute("username", uname);
            session.setAttribute("empId",  employee.getId());

            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            try {
                ec.redirect(ec.getRequestContextPath() + "/pages/welcome.xhtml");
            } catch (IOException e) {

            }
        } else {

            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Invalid Login!",
                            "Please Try Again!"));

            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            try {
                ec.redirect(ec.getRequestContextPath() + "/login.xhtml");
            } catch (IOException e) {

            }
        }
    }

    public String logout() {
        HttpSession session = Util.getSession();
        session.invalidate();
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ec.redirect(ec.getRequestContextPath() + "/login.xhtml");
        } catch (IOException e) {

        }
        return "login";
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}