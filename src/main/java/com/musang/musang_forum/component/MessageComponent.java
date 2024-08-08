package com.musang.musang_forum.component;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class MessageComponent {
    public static HBox setOwnMessageBox(String message) {
        Text text = new Text(message);
        TextFlow textFlow = new TextFlow(text);
        HBox messageBox = new HBox(textFlow);
        text.setStyle("-fx-font-family: 'Segoe UI'; -fx-font-size: 16; -fx-fill: white");
        textFlow.setStyle("-fx-background-color: #7d6c54; -fx-background-radius: 10px; -fx-padding: 10px; -fx-max-width: 600px; ");
        messageBox.setStyle("-fx-alignment: center-right");

        return messageBox;
    }

    public static HBox setOthersMessageBox(String username, String message) {
        Text messageText = new Text(message);
        TextFlow messageTf = new TextFlow(messageText);
        messageText.setStyle("-fx-font-family: 'Segoe UI'; -fx-font-size: 16");
        messageTf.setStyle("-fx-background-color: #E8E8E8; -fx-background-radius: 0 0 10 10; -fx-padding: 0 10 10 10");
        messageTf.setMaxWidth(600);

        Text usernameText = new Text(username);
        TextFlow usernameTf = new TextFlow(usernameText);
        usernameText.setStyle("-fx-font-family: 'Segoe UI Semibold'; -fx-font-size: 16; -fx-text-fill: #8300ff");
        usernameTf.setStyle("-fx-background-color: #E8E8E8; -fx-background-radius: 10 10 0 0; -fx-padding: 10 10 0 10");

        double usernameWidth = computeTextWidth(usernameText.getFont(), usernameText.getText(), 0.0);
        double defaultMinWidth = 80;
        messageTf.setMinWidth(Math.max(usernameWidth + 20, defaultMinWidth));

        VBox container = new VBox(usernameTf, messageTf);
        container.setAlignment(Pos.CENTER_LEFT);

        HBox messageBox = new HBox(container);
        messageBox.setAlignment(Pos.CENTER_LEFT);

        return messageBox;
    }

    private static double computeTextWidth(Font font, String text, double wrappingWidth) {
        Text tempText = new Text(text);
        tempText.setFont(font);
        if (wrappingWidth > 0) {
            tempText.setWrappingWidth(wrappingWidth);
        }
        return tempText.getBoundsInLocal().getWidth();
    }
}
