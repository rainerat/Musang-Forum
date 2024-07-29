package com.musang.musang_forum.controller;

import com.musang.musang_forum.model.CurrentUser;
import com.musang.musang_forum.model.User;
import com.musang.musang_forum.server.Database;
import com.musang.musang_forum.service.EncryptionService;
import javafx.stage.Stage;

public abstract class Controller {
    protected Stage stage;
    protected Database database;
    protected CurrentUser currentUser;
    protected EncryptionService encryptionService;

    public Controller() {
        database = Database.getInstance();
        currentUser = CurrentUser.getInstance();
        encryptionService = new EncryptionService();
    }



}
