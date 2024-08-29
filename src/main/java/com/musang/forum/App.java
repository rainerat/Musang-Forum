package com.musang.forum;

import com.musang.forum.client.Client;
import com.musang.forum.util.SessionManager;
import com.musang.forum.model.User;

public class App {
    public User getCurrentUser() {
        return SessionManager.getCurrentUser();
    }

    public Client getClient() {
        return SessionManager.getClient();
    }
}
