package com.movieticket.strategy;

// # Class: UPIPaymentStrategy
// # Purpose: Handles upipaymentstrategy logic in this module.
public class UPIPaymentStrategy implements PaymentStrategy {
    @Override
// # Method: processPayment
// # Purpose: Executes the processPayment workflow for this class.
    public boolean processPayment(String bookingId, double amount) {
        System.out.println("[UPI GATEWAY] Processed payment of ₹" + amount + " for Booking: " + bookingId);
        return true;
    }
    @Override
// # Method: processRefund
// # Purpose: Executes the processRefund workflow for this class.
    public boolean processRefund(String bookingId, double amount) {
        System.out.println("[UPI GATEWAY] Processed refund of ₹" + amount + " for Booking: " + bookingId);
        return true;
    }
}