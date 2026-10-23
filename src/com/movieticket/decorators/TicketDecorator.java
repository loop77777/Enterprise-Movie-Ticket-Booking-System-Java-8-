package com.movieticket.decorators;

public abstract class TicketDecorator implements TicketComponent {
    protected final TicketComponent wrappedTicket;

// # Method: TicketDecorator
// # Purpose: Executes the TicketDecorator workflow for this class.
    public TicketDecorator(TicketComponent ticket) {
        this.wrappedTicket = ticket;
    }
}