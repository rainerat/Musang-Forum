package com.musang.musang_forum.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ForumController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}