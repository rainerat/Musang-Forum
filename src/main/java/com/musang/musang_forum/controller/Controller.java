package com.musang.musang_forum.controller;

import com.musang.musang_forum.App;
import com.musang.musang_forum.Main;
import com.musang.musang_forum.controller.component.MessageBubbleController;
import com.musang.musang_forum.util.PageManager;
import com.musang.musang_forum.util.StageManager;
import com.musang.musang_forum.util.ThemeManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.IOException;


public abstract class Controller {
    private final App app = Main.MUSANG_APP;

    public Controller(final String PATH) {
        PageManager.setCurrentPage(PATH);
        ThemeManager.setCurrentTheme(ThemeManager.getAll().getFirst());
    }

    protected FXMLLoader getLoader(final String PATH) throws IOException {
        return new FXMLLoader(Main.class.getResource(PATH));
    }

    protected Controller loadPage(final String PATH) throws IOException {
        PageManager.setPreviousPagePath(PageManager.getCurrentPagePath());
        FXMLLoader loader = getLoader(PATH);
        StageManager.getPrimaryStage().setScene(new Scene(loader.load()));
        return loader.getController();
    }

    protected Controller loadNestedPage(final String NESTED_PATH, Pane centerPane) {
        try {
            FXMLLoader loader = getLoader(NESTED_PATH);
            Pane pane = loader.load();
            centerPane.getChildren().setAll(pane);
            return loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @FXML
    protected void loadPreviousPage() throws IOException {
        StageManager.getPrimaryStage().setScene(new Scene(getLoader(PageManager.getPreviousPagePath()).load()));
    }

    protected App app() {
        return app;
    }
}
