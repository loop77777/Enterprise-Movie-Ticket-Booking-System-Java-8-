package com.movieticket.decorators;


// # Class: PopcornComboDecorator
// # Purpose: Handles popcorncombodecorator logic in this module.
public class PopcornComboDecorator extends TicketDecorator {
// # Method: PopcornComboDecorator
// # Purpose: Executes the PopcornComboDecorator workflow for this class.
    public PopcornComboDecorator(TicketComponent ticket) {
        super(ticket);
    }

    @Override
// # Method: getCost
// # Purpose: Executes the getCost workflow for this class.
    public double getCost() {
        return wrappedTicket.getCost() + 150.0;
    }

    @Override
// # Method: getDescription
// # Purpose: Executes the getDescription workflow for this class.
    public String getDescription() {
        return wrappedTicket.getDescription() + " + Popcorn Combo [₹150]";
    }
}