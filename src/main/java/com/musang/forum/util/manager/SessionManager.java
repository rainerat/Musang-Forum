package com.musang.forum.util.manager;

import com.musang.forum.client.Client;
import com.musang.forum.model.User;

import java.sql.Date;

public class SessionManager {
    private static User currentUser;
    private static Client client;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User user) {
        SessionManager.currentUser = user;
    }

    public static void updateCurrentUser(String username, String displayName, Date dob, String email) {
        SessionManager.currentUser.setUsername(username);
        SessionManager.currentUser.setDisplayName(displayName);
        SessionManager.currentUser.setDob(dob);
        SessionManager.currentUser.setEmail(email);
    }

    public static Client getClient() {
        return client;
    }

    public static void setClient(Client client) {
        SessionManager.client = client;
    }

    public static void close() {
        currentUser = null;
        client.close();
        client = null;
        System.out.println("Session closed");
    }
}
