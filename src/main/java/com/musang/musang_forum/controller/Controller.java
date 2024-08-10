package com.musang.musang_forum.controller;

import com.musang.musang_forum.App;
import com.musang.musang_forum.Main;
import com.musang.musang_forum.util.PageManager;
import com.musang.musang_forum.util.StageManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;


public abstract class Controller {
    private final App app = Main.MUSANG_APP;

    public Controller(final String PATH) {
        PageManager.setCurrentPage(PATH);
    }

    protected Controller loadPage(final String PATH) throws IOException {
        PageManager.setPreviousPagePath(PageManager.getCurrentPagePath());
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(PATH));
        StageManager.getPrimaryStage().setScene(new Scene(loader.load()));
        return loader.getController();
    }

    @FXML
    protected void loadPreviousPage() throws IOException {
        StageManager.getPrimaryStage().setScene(new Scene(new FXMLLoader(Main.class.getResource(PageManager.getPreviousPagePath())).load()));
    }

    protected App app() {
        return app;
    }

}
