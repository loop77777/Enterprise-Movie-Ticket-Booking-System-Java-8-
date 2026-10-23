package com.movieticket.decorators;


// # Class: BaseTicket
// # Purpose: Handles baseticket logic in this module.
public class BaseTicket implements TicketComponent {
    private final String seatInfo;
    private final double price;

// # Method: BaseTicket
// # Purpose: Executes the BaseTicket workflow for this class.
    public BaseTicket(String seatInfo, double price) {
        this.seatInfo = seatInfo;
        this.price = price;
    }

    @Override
// # Method: getCost
// # Purpose: Executes the getCost workflow for this class.
    public double getCost() {
        return price;
    }

    @Override
// # Method: getDescription
// # Purpose: Executes the getDescription workflow for this class.
    public String getDescription() {
        return "Base Seats " + seatInfo + " [₹" + price + "]";
    }
}