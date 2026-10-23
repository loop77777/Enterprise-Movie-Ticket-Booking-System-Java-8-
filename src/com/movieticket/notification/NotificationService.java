package com.movieticket.notification;

import com.movieticket.model.User;

// # Interface: NotificationService
// # Purpose: Handles notificationservice logic in this module.
public interface NotificationService {
    void sendNotification(User user, String message);
}