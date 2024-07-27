package com.musang.musang_forum.controller;

import com.musang.musang_forum.server.Database;
import javafx.stage.Stage;

public abstract class Controller {
    protected Database database = Database.getInstance();
    protected Stage stage;
}
