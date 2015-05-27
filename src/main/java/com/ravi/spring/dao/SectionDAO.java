package com.ravi.spring.dao;

import com.ravi.spring.model.Section;

import java.util.List;

/**
 * Created by User on 27.05.2015.
 */
public interface SectionDAO {

    public void addSection(Section section);

    public List<Section> getSections();

    public void updateSection(Section section);

    public void updateSections(List<Section> sections);

    public List<Section> getApprovedSections();
}
