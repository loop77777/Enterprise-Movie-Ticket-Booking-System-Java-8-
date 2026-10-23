package com.movieticket.model;

import com.movieticket.enums.BookingStatus;
import java.util.List;

// # Class: Booking
// # Purpose: Handles booking logic in this module.
public class Booking {
    private final String bookingId;
    private final Customer customer;
    private final Show show;
    private final List<ShowSeat> bookedSeats;
    private double totalAmount;
    private BookingStatus status;
    private Ticket ticket;
    private Payment payment;

// # Method: Booking
// # Purpose: Executes the Booking workflow for this class.
    public Booking(String bookingId, Customer customer, Show show, List<ShowSeat> bookedSeats, double totalAmount) {
        this.bookingId = bookingId;
        this.customer = customer;
        this.show = show;
        this.bookedSeats = bookedSeats;
        this.totalAmount = totalAmount;
        this.status = BookingStatus.PENDING;
    }

// # Method: getBookingId
// # Purpose: Executes the getBookingId workflow for this class.
    public String getBookingId() { return bookingId; }
// # Method: getCustomer
// # Purpose: Executes the getCustomer workflow for this class.
    public Customer getCustomer() { return customer; }
// # Method: getShow
// # Purpose: Executes the getShow workflow for this class.
    public Show getShow() { return show; }
// # Method: getBookedSeats
// # Purpose: Executes the getBookedSeats workflow for this class.
    public List<ShowSeat> getBookedSeats() { return bookedSeats; }
// # Method: getTotalAmount
// # Purpose: Executes the getTotalAmount workflow for this class.
    public double getTotalAmount() { return totalAmount; }
// # Method: setTotalAmount
// # Purpose: Executes the setTotalAmount workflow for this class.
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
// # Method: getStatus
// # Purpose: Executes the getStatus workflow for this class.
    public BookingStatus getStatus() { return status; }
// # Method: setStatus
// # Purpose: Executes the setStatus workflow for this class.
    public void setStatus(BookingStatus status) { this.status = status; }
// # Method: setTicket
// # Purpose: Executes the setTicket workflow for this class.
    public void setTicket(Ticket ticket) { this.ticket = ticket; }
// # Method: setPayment
// # Purpose: Executes the setPayment workflow for this class.
    public void setPayment(Payment payment) { this.payment = payment; }
}