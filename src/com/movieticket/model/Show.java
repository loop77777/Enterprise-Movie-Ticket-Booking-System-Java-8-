package com.movieticket.model;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.ReentrantLock;

// # Class: Show
// # Purpose: Handles show logic in this module.
public class Show {
    private final String id;
    private final Movie movie;
    private final Theatre theatre;
    private final Screen screen;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final Map<String, ShowSeat> showSeats;
    private final ConcurrentLinkedQueue<String> waitingListQueue;
    private final ReentrantLock showLock;

// # Method: Show
// # Purpose: Executes the Show workflow for this class.
    public Show(String id, Movie movie, Theatre theatre, Screen screen, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = id;
        this.movie = movie;
        this.theatre = theatre;
        this.screen = screen;
        this.startTime = startTime;
        this.endTime = endTime;
        this.waitingListQueue = new ConcurrentLinkedQueue<>();
        this.showLock = new ReentrantLock();

        this.showSeats = new ConcurrentHashMap<>();
        screen.getSeats().forEach((seatId, seat) ->
                this.showSeats.put(seatId, new ShowSeat(seat.getSeatId(), seat.getSeatType(), seat.getPrice()))
        );
    }

// # Method: getId
// # Purpose: Executes the getId workflow for this class.
    public String getId() { return id; }
// # Method: getMovie
// # Purpose: Executes the getMovie workflow for this class.
    public Movie getMovie() { return movie; }
// # Method: getTheatre
// # Purpose: Executes the getTheatre workflow for this class.
    public Theatre getTheatre() { return theatre; }
// # Method: getStartTime
// # Purpose: Executes the getStartTime workflow for this class.
    public LocalDateTime getStartTime() { return startTime; }
// # Method: getShowSeats
// # Purpose: Executes the getShowSeats workflow for this class.
    public Map<String, ShowSeat> getShowSeats() { return showSeats; }
// # Method: getWaitingListQueue
// # Purpose: Executes the getWaitingListQueue workflow for this class.
    public ConcurrentLinkedQueue<String> getWaitingListQueue() { return waitingListQueue; }
// # Method: getShowLock
// # Purpose: Executes the getShowLock workflow for this class.
    public ReentrantLock getShowLock() { return showLock; }
}