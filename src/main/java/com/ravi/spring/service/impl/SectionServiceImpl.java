package com.ravi.spring.service.impl;

import com.ravi.spring.dao.ProjectDAO;
import com.ravi.spring.dao.SectionDAO;
import com.ravi.spring.model.Section;
import com.ravi.spring.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.List;

/**
 * Created by User on 27.05.2015.
 */

@Service("sectionService")
@Transactional
@ManagedBean(name = "sectionService")
@ApplicationScoped
public class SectionServiceImpl implements SectionService {

    @Autowired
    private SectionDAO sectionDAO;

    public SectionDAO getSectionDAO() {
        return sectionDAO;
    }

    public void setSectionDAO(SectionDAO sectionDAO) {
        this.sectionDAO = sectionDAO;
    }

    @Override
    public void addSection(Section section) {
        sectionDAO.addSection(section);
    }

    @Override
    public List<Section> getSections() {
        return sectionDAO.getSections();
    }

    @Override
    public void updateSection(Section section) {

    }

    @Override
    public void updateSections(List<Section> sections) {

    }

    @Override
    public List<Section> getApprovedSections() {
        return null;
    }

    @Override
    public Section getSectionById(Integer selectedSectionId) {
        return sectionDAO.getSectionById(selectedSectionId);
    }
}