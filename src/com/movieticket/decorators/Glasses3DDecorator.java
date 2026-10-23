package com.movieticket.decorators;

// # Class: Glasses3DDecorator
// # Purpose: Handles glasses3ddecorator logic in this module.
public class Glasses3DDecorator extends TicketDecorator {
// # Method: Glasses3DDecorator
// # Purpose: Executes the Glasses3DDecorator workflow for this class.
    public Glasses3DDecorator(TicketComponent ticket) {
        super(ticket);
    }

    @Override
// # Method: getCost
// # Purpose: Executes the getCost workflow for this class.
    public double getCost() {
        return wrappedTicket.getCost() + 30.0;
    }

    @Override
// # Method: getDescription
// # Purpose: Executes the getDescription workflow for this class.
    public String getDescription() {
        return wrappedTicket.getDescription() + " + 3D Glasses Rental [₹30]";
    }
}