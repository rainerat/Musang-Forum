package com.musang.forum;

import com.musang.forum.client.Client;
import com.musang.forum.service.NotificationService;
import com.musang.forum.util.manager.SessionManager;
import com.musang.forum.model.User;

public class App {
    private final NotificationService notificationService;

    public App() {
        this.notificationService = new NotificationService();
    }

    public NotificationService getNotificationService() {
        return notificationService;
    }

    public User getCurrentUser() {
        return SessionManager.getCurrentUser();
    }

    public Client getClient() {
        return SessionManager.getClient();
    }
}
