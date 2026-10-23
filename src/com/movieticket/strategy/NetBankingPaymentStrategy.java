package com.movieticket.strategy;


// # Class: NetBankingPaymentStrategy
// # Purpose: Handles netbankingpaymentstrategy logic in this module.
public class NetBankingPaymentStrategy implements PaymentStrategy {
    @Override
// # Method: processPayment
// # Purpose: Executes the processPayment workflow for this class.
    public boolean processPayment(String bookingId, double amount) {
        System.out.println("[NETBANKING GATEWAY] Processed payment of ₹" + amount + " for Booking: " + bookingId);
        return true;
    }
    @Override
// # Method: processRefund
// # Purpose: Executes the processRefund workflow for this class.
    public boolean processRefund(String bookingId, double amount) {
        System.out.println("[NETBANKING GATEWAY] Processed refund of ₹" + amount + " for Booking: " + bookingId);
        return true;
    }
}