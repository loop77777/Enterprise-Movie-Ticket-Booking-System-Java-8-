package com.movieticket.model;

import com.movieticket.enums.UserRole;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

// # Class: Customer
// # Purpose: Handles customer logic in this module.
public class Customer extends User {
    private final List<Booking> bookingHistory;

// # Method: Customer
// # Purpose: Executes the Customer workflow for this class.
    public Customer(String id, String name, String email, String phone, String password) {
        super(id, name, email, phone, password, UserRole.CUSTOMER);
        this.bookingHistory = new CopyOnWriteArrayList<>();
    }

// # Method: getBookings
// # Purpose: Executes the getBookings workflow for this class.
    public List<Booking> getBookings() { return bookingHistory; }
// # Method: addBooking
// # Purpose: Executes the addBooking workflow for this class.
    public void addBooking(Booking booking) { this.bookingHistory.add(booking); }
}