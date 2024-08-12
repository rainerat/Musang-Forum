package com.musang.musang_forum.controller.component;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class MessageBubbleController {
    @FXML
    private HBox messageBox;

    @FXML
    private VBox container;

    @FXML
    private TextFlow usernameFlow;

    @FXML
    private Text usernameText;

    @FXML
    private TextFlow messageFlow;

    @FXML
    private Text messageText;

    public void setMessage(String username, String message, boolean isOwnMessage) {
        if (isOwnMessage) {
            container.getChildren().removeFirst();
            messageFlow.setStyle("-fx-background-color: #7d6c54; -fx-background-radius: 10; -fx-padding: 10; -fx-max-width: 600;");
            messageText.setStyle("-fx-font-family: 'Segoe UI'; -fx-font-size: 16; -fx-fill: white");
            messageBox.setStyle("-fx-alignment: center-right;");
        } else {
            usernameText.setText(username);
            usernameText.setStyle("-fx-font-family: 'Segoe UI Semibold'; -fx-font-size: 16; -fx-fill: #8300ff;");
            usernameFlow.setStyle("-fx-background-color: #E8E8E8; -fx-background-radius: 10 10 0 0; -fx-padding: 10 10 0 10");
            messageFlow.setStyle("-fx-background-color: #E8E8E8; -fx-background-radius: 0 0 10 10; -fx-padding: 0 10 10 10;");
            messageText.setStyle("-fx-font-family: 'Segoe UI'; -fx-font-size: 16");
            container.setStyle("-fx-alignment: center-left;");
            messageBox.setStyle("-fx-alignment: center-left;");
        }

        messageText.setText(message);
    }
}