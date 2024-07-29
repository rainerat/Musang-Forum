package com.musang.musang_forum.controller;

import com.musang.musang_forum.model.CurrentUser;
import com.musang.musang_forum.model.User;
import com.musang.musang_forum.server.Database;
import javafx.stage.Stage;

public abstract class Controller {
    protected Database database;
    protected CurrentUser currentUser;
    protected Stage stage;

    public Controller() {
        database = Database.getInstance();
        currentUser = CurrentUser.getInstance();
    }

}
