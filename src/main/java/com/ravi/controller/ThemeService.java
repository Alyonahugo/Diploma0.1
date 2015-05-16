package com.ravi.controller;

/**
 * Created by User on 16.05.2015.
 */
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
        projects.add(new Project(0, "Afterdark", "afterdark"));
        projects.add(new Project(1, "Afternoon", "afternoon"));
        projects.add(new Project(2, "Afterwork", "afterwork"));
        projects.add(new Project(3, "Aristo", "aristo"));
        projects.add(new Project(4, "Black-Tie", "black-tie"));
        projects.add(new Project(5, "Blitzer", "blitzer"));
        projects.add(new Project(6, "Bluesky", "bluesky"));
        projects.add(new Project(7, "Bootstrap", "bootstrap"));
        projects.add(new Project(8, "Casablanca", "casablanca"));
        projects.add(new Project(9, "Cupertino", "cupertino"));
        projects.add(new Project(10, "Cruze", "cruze"));
        projects.add(new Project(11, "Dark-Hive", "dark-hive"));
        projects.add(new Project(12, "Delta", "delta"));
        projects.add(new Project(13, "Dot-Luv", "dot-luv"));
        projects.add(new Project(14, "Eggplant", "eggplant"));
        projects.add(new Project(15, "Excite-Bike", "excite-bike"));
        projects.add(new Project(16, "Flick", "flick"));
        projects.add(new Project(17, "Glass-X", "glass-x"));
        projects.add(new Project(18, "Home", "home"));
        projects.add(new Project(19, "Hot-Sneaks", "hot-sneaks"));
        projects.add(new Project(20, "Humanity", "humanity"));
        projects.add(new Project(21, "Le-Frog", "le-frog"));
        projects.add(new Project(22, "Midnight", "midnight"));
        projects.add(new Project(23, "Mint-Choc", "mint-choc"));
        projects.add(new Project(24, "Overcast", "overcast"));
        projects.add(new Project(25, "Pepper-Grinder", "pepper-grinder"));
        projects.add(new Project(26, "Redmond", "redmond"));
        projects.add(new Project(27, "Rocket", "rocket"));
        projects.add(new Project(28, "Sam", "sam"));
        projects.add(new Project(29, "Smoothness", "smoothness"));
        projects.add(new Project(30, "South-Street", "south-street"));
        projects.add(new Project(31, "Start", "start"));
        projects.add(new Project(32, "Sunny", "sunny"));
        projects.add(new Project(33, "Swanky-Purse", "swanky-purse"));
        projects.add(new Project(34, "Trontastic", "trontastic"));
        projects.add(new Project(35, "UI-Darkness", "ui-darkness"));
        projects.add(new Project(36, "UI-Lightness", "ui-lightness"));
        projects.add(new Project(37, "Vader", "vader"));
    }

    public List<Project> getProjects() {
        return projects;
    }
}