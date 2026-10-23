package com.movieticket.notification;

import com.movieticket.model.User;

// # Class: SMSNotificationService
// # Purpose: Handles smsnotificationservice logic in this module.
public class SMSNotificationService implements NotificationService {
    @Override
// # Method: sendNotification
// # Purpose: Executes the sendNotification workflow for this class.
    public void sendNotification(User user, String message) {
        System.out.println("[SMS ALERT -> " + user.getPhone() + "]: " + message);
    }
}