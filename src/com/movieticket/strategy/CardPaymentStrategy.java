package com.movieticket.strategy;

// # Class: CardPaymentStrategy
// # Purpose: Handles cardpaymentstrategy logic in this module.
public class CardPaymentStrategy implements PaymentStrategy {
    @Override
// # Method: processPayment
// # Purpose: Executes the processPayment workflow for this class.
    public boolean processPayment(String bookingId, double amount) {
        System.out.println("[CARD GATEWAY] Processed payment of ₹" + amount + " for Booking: " + bookingId);
        return true;
    }
    @Override
// # Method: processRefund
// # Purpose: Executes the processRefund workflow for this class.
    public boolean processRefund(String bookingId, double amount) {
        System.out.println("[CARD GATEWAY] Processed refund of ₹" + amount + " for Booking: " + bookingId);
        return true;
    }
}