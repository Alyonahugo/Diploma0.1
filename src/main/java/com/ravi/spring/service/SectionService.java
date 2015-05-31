package com.ravi.spring.service;

import com.ravi.spring.model.Project;
import com.ravi.spring.model.Section;

import java.util.List;

/**
 * Created by User on 27.05.2015.
 */
public interface SectionService {
    void addSection(Section section);

    List<Section> getSections();

    void updateSection(Section section);

    void updateSections(List<Section> sections);

    List<Section> getApprovedSections();

}