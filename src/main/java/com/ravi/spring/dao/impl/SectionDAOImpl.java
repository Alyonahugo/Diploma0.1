package com.ravi.spring.dao.impl;

import com.ravi.spring.dao.SectionDAO;
import com.ravi.spring.model.Section;
import com.ravi.spring.service.SectionService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by User on 27.05.2015.
 */


@Repository
public class SectionDAOImpl implements SectionDAO {

    @Autowired
    private SessionFactory sessionFactory;



    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addSection(Section section) {
        sessionFactory.getCurrentSession().save(section);
    }

    @Override
    public List<Section> getSections() {
        return sessionFactory.getCurrentSession().createQuery("from Section").list();
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
        List<Section> list = sessionFactory.getCurrentSession().createQuery("from Section where sec_id = ?").setParameter(0, selectedSectionId).list();
        if (list != null) {
            return list.get(0);
        }
        return null;
    }
}