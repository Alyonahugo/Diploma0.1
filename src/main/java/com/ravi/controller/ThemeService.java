package com.ravi.controller;

/**
 * Created by User on 16.05.2015.
 */
import com.ravi.spring.model.Project;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;


@ManagedBean(name="themeService", eager = true)
@ApplicationScoped
public class ThemeService {

    private List<Project> projects;

    @PostConstruct
    public void init() {
        projects = new ArrayList<Project>();
        projects.add(new Project(0, "Afterdark"));
        projects.add(new Project(1, "Afternoon"));
        projects.add(new Project(2, "Afterwork"));
        projects.add(new Project(3, "Aristo"));
        projects.add(new Project(4, "Black-Tie"));
        projects.add(new Project(5, "Blitzer"));
        projects.add(new Project(6, "Bluesky"));
        projects.add(new Project(7, "Bootstrap"));
        projects.add(new Project(8, "Casablanca"));
        projects.add(new Project(9, "Cupertino"));
        projects.add(new Project(10, "Cruze"));
        projects.add(new Project(11, "Dark-Hive"));
        projects.add(new Project(12, "Delta"));
        projects.add(new Project(13, "Dot-Luv"));
        projects.add(new Project(14, "Eggplant"));
    }

    public List<Project> getProjects() {
        return projects;
    }
}