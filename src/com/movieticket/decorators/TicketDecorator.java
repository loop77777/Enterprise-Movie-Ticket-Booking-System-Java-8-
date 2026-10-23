package com.movieticket.decorators;

public abstract class TicketDecorator implements TicketComponent {
    protected final TicketComponent wrappedTicket;

    public TicketDecorator(TicketComponent ticket) {
        this.wrappedTicket = ticket;
    }
}