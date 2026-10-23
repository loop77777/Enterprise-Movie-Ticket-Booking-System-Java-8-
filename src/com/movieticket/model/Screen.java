package com.movieticket.model;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// # Class: Screen
// # Purpose: Handles screen logic in this module.
public class Screen {
    private final String id;
    private final String name;
    private final Map<String, Seat> seats;

// # Method: Screen
// # Purpose: Executes the Screen workflow for this class.
    public Screen(String id, String name, List<Seat> seatList) {
        this.id = id;
        this.name = name;
        this.seats = new ConcurrentHashMap<>();
        seatList.forEach(seat -> this.seats.put(seat.getSeatId(), seat));
    }

// # Method: getId
// # Purpose: Executes the getId workflow for this class.
    public String getId() { return id; }
// # Method: getName
// # Purpose: Executes the getName workflow for this class.
    public String getName() { return name; }
// # Method: getSeats
// # Purpose: Executes the getSeats workflow for this class.
    public Map<String, Seat> getSeats() { return seats; }
}