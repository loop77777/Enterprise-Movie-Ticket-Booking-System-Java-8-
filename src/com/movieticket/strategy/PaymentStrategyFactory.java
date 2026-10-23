package com.movieticket.strategy;

import com.movieticket.enums.PaymentMethod;

// # Class: PaymentStrategyFactory
// # Purpose: Handles paymentstrategyfactory logic in this module.
public class PaymentStrategyFactory {
// # Method: getStrategy
// # Purpose: Executes the getStrategy workflow for this class.
    public static PaymentStrategy getStrategy(PaymentMethod method) {
        switch (method) {
            case UPI: return new UPIPaymentStrategy();
            case CREDIT_CARD:
            case DEBIT_CARD: return new CardPaymentStrategy();
            case NET_BANKING: return new NetBankingPaymentStrategy();
            default: return new UPIPaymentStrategy();
        }
    }
}