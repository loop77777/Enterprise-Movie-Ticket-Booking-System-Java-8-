package com.movieticket.strategy;

// # Interface: PaymentStrategy
// # Purpose: Handles paymentstrategy logic in this module.
public interface PaymentStrategy {
    boolean processPayment(String bookingId, double amount);
    boolean processRefund(String bookingId, double amount);
}