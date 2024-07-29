package com.musang.musang_forum.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;

public class ForumController {
    @FXML
    private HBox myAccountBox;

    @FXML
    protected void onMyAccountEntered() {
        myAccountBox.setStyle("-fx-background-color: #efefef; -fx-background-radius: 10; -fx-cursor: hand;");
    }

    @FXML
    protected void onMyAccountExited() {
        myAccountBox.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 10; -fx-cursor: default");
    }
}