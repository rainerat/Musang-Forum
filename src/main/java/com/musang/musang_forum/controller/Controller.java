package com.musang.musang_forum.controller;

import com.musang.musang_forum.App;
import com.musang.musang_forum.Main;
import com.musang.musang_forum.util.StageManager;
import javafx.stage.Stage;

public abstract class Controller {
    private final App app = Main.MUSANG_APP;;

    protected Stage getStage() {
        return StageManager.getPrimaryStage();
    }

    protected App app() {
        return app;
    }
}
