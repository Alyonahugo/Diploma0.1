package com.ravi.controller;

import com.ravi.spring.model.Section;
import com.ravi.spring.service.CommentService;
import com.ravi.spring.service.SectionService;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * Created by User on 27.05.2015.
 */

@ManagedBean(name="sectionBean")
public class SectionBean implements Serializable {

    private List<Section> sections;
    private String secName;

@Autowired
    private Section selectedSection;


    @Autowired
    @ManagedProperty(value="#{sectionService}")
    private SectionService sectionService;




  //  @PostConstruct
    public  void init(){
        sections = sectionService.getSections();
    }

    public void addSection() throws IOException {
        Section section = new Section();
        section.setName(secName);
        sectionService.addSection(section);
        System.out.println("method addsection work");
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(ec.getRequestContextPath() + "/pages/vote.xhtml");
    //    return "vote.xhtml";
    }
    public List<Section> getSections() {
        init();
        return sections;
    }

    public void onRowSelect(SelectEvent event) {
        System.out.println("method work - onRowSelect");
    }

    public void onRowUnselect(UnselectEvent event) {
        System.out.println("onRowUnselect have worked");
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public SectionService getSectionService() {
        return sectionService;
    }

    public void setSectionService(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    public String getSecName() {
        return secName;
    }

    public void setSecName(String secName) {
        this.secName = secName;
    }

    public Section getSelectedSection() {
        return selectedSection;
    }

    public void setSelectedSection(Section selectedSection) {
        this.selectedSection = selectedSection;


        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ec.redirect(ec.getRequestContextPath() + "/pages/topic.xhtml");
        } catch (IOException e) {

        }

    }
}
