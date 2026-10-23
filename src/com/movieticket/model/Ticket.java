package com.movieticket.model;

import java.time.LocalDateTime;
import java.util.List;

// # Class: Ticket
// # Purpose: Handles ticket logic in this module.
public class Ticket {
    private final String ticketId;
    private final String bookingId;
    private final String movieTitle;
    private final String theatreName;
    private final List<String> seatIds;
    private final LocalDateTime showTime;

// # Method: Ticket
// # Purpose: Executes the Ticket workflow for this class.
    public Ticket(String ticketId, String bookingId, String movieTitle, String theatreName, List<String> seatIds, LocalDateTime showTime) {
        this.ticketId = ticketId;
        this.bookingId = bookingId;
        this.movieTitle = movieTitle;
        this.theatreName = theatreName;
        this.seatIds = seatIds;
        this.showTime = showTime;
    }

// # Method: getTicketId
// # Purpose: Executes the getTicketId workflow for this class.
    public String getTicketId() { return ticketId; }

    @Override
// # Method: toString
// # Purpose: Executes the toString workflow for this class.
    public String toString() {
        return "E-TICKET [TicketID=" + ticketId + ", BookingID=" + bookingId + ", Movie=" + movieTitle +
                ", Theatre=" + theatreName + ", Seats=" + seatIds + ", ShowTime=" + showTime + "]";
    }
}
