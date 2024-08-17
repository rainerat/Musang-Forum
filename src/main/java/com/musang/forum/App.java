package com.musang.forum;

import com.musang.forum.client.Client;
import com.musang.forum.model.CurrentUser;
import com.musang.forum.model.User;
import com.musang.forum.service.EncryptionService;
import com.musang.forum.util.ClientManager;

public class App {
    private final EncryptionService encryptionService;

    public App() {
        encryptionService = new EncryptionService();
    }

    public EncryptionService getEncryptionService() {
        return encryptionService;
    }

    public User getCurrentUser() {
        return CurrentUser.get();
    }

    public Client getClient() {
        return ClientManager.getClient();
    }
}
