package com.ravi.controller;

import com.ravi.spring.model.Section;
import com.ravi.spring.service.CommentService;
import com.ravi.spring.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
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
    @ManagedProperty(value="#{sectionService}")
    private SectionService sectionService;

  //  @PostConstruct
    public  void init(){
        sections = sectionService.getSections();
    }

    public void addSection(){
        Section section = new Section();
        section.setName(secName);
        sectionService.addSection(section);
        System.out.println("method addsection work");
    }
    public List<Section> getSections() {
        init();
        return sections;
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
}
