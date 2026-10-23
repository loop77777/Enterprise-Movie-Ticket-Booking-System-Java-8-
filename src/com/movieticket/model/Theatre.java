package com.movieticket.model;

import java.util.List;

// # Class: Theatre
// # Purpose: Handles theatre logic in this module.
public class Theatre {
    private final String id;
    private final String name;
    private final String city;
    private final String location;
    private final List<Screen> screens;

// # Method: Theatre
// # Purpose: Executes the Theatre workflow for this class.
    public Theatre(String id, String name, String city, String location, List<Screen> screens) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.location = location;
        this.screens = screens;
    }

// # Method: getId
// # Purpose: Executes the getId workflow for this class.
    public String getId() { return id; }
// # Method: getName
// # Purpose: Executes the getName workflow for this class.
    public String getName() { return name; }
// # Method: getCity
// # Purpose: Executes the getCity workflow for this class.
    public String getCity() { return city; }
// # Method: getLocation
// # Purpose: Executes the getLocation workflow for this class.
    public String getLocation() { return location; }
// # Method: getScreens
// # Purpose: Executes the getScreens workflow for this class.
    public List<Screen> getScreens() { return screens; }
}