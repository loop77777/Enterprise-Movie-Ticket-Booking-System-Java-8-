package com.movieticket.model;

import com.movieticket.enums.SeatType;

// # Class: Seat
// # Purpose: Handles seat logic in this module.
public class Seat {
    private final String seatId;
    private final int row;
    private final int column;
    private final SeatType seatType;
    private final double price;

// # Method: Seat
// # Purpose: Executes the Seat workflow for this class.
    public Seat(String seatId, int row, int column, SeatType seatType, double price) {
        this.seatId = seatId;
        this.row = row;
        this.column = column;
        this.seatType = seatType;
        this.price = price;
    }

// # Method: getSeatId
// # Purpose: Executes the getSeatId workflow for this class.
    public String getSeatId() { return seatId; }
// # Method: getSeatType
// # Purpose: Executes the getSeatType workflow for this class.
    public SeatType getSeatType() { return seatType; }
// # Method: getPrice
// # Purpose: Executes the getPrice workflow for this class.
    public double getPrice() { return price; }
}