package com.movieticket.model;


import com.movieticket.enums.PaymentMethod;
import com.movieticket.enums.PaymentStatus;

// # Class: Payment
// # Purpose: Handles payment logic in this module.
public class Payment {
    private final String paymentId;
    private final String bookingId;
    private final double amount;
    private final PaymentMethod method;
    private PaymentStatus status;

// # Method: Payment
// # Purpose: Executes the Payment workflow for this class.
    public Payment(String paymentId, String bookingId, double amount, PaymentMethod method) {
        this.paymentId = paymentId;
        this.bookingId = bookingId;
        this.amount = amount;
        this.method = method;
        this.status = PaymentStatus.PENDING;
    }

// # Method: getStatus
// # Purpose: Executes the getStatus workflow for this class.
    public PaymentStatus getStatus() { return status; }
// # Method: setStatus
// # Purpose: Executes the setStatus workflow for this class.
    public void setStatus(PaymentStatus status) { this.status = status; }
}