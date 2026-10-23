package com.movieticket.notification;

import com.movieticket.model.User;

// # Class: EmailNotificationService
// # Purpose: Handles emailnotificationservice logic in this module.
public class EmailNotificationService implements NotificationService {
    @Override
// # Method: sendNotification
// # Purpose: Executes the sendNotification workflow for this class.
    public void sendNotification(User user, String message) {
        System.out.println("[EMAIL -> " + user.getEmail() + "]: " + message);
    }
}