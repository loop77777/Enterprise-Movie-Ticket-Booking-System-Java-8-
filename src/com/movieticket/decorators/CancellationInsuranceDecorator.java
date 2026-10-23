package com.movieticket.decorators;

// # Class: CancellationInsuranceDecorator
// # Purpose: Handles cancellationinsurancedecorator logic in this module.
public class CancellationInsuranceDecorator extends TicketDecorator {
// # Method: CancellationInsuranceDecorator
// # Purpose: Executes the CancellationInsuranceDecorator workflow for this class.
    public CancellationInsuranceDecorator(TicketComponent ticket) {
        super(ticket);
    }

    @Override
// # Method: getCost
// # Purpose: Executes the getCost workflow for this class.
    public double getCost() {
        return wrappedTicket.getCost() + 20.0;
    }

    @Override
// # Method: getDescription
// # Purpose: Executes the getDescription workflow for this class.
    public String getDescription() {
        return wrappedTicket.getDescription() + " + Ticket Insurance [₹20]";
    }
}