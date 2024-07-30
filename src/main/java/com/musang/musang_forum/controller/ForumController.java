package com.musang.musang_forum.controller;

import com.musang.musang_forum.client.Client;
import com.musang.musang_forum.model.Forum;
import com.musang.musang_forum.repo.ForumRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class ForumController {
    @FXML
    private HBox myAccountBox;

    @FXML
    private Label forumTitleLabel;

    @FXML
    private Label forumDescLabel;

    @FXML
    private Label viewMoreLabel;

    @FXML
    private VBox chatBox;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private TextField messageField;

    @FXML
    private Button sendButton;

    private Client client;

    private Forum chosenForum = ForumRepository.findByTitle("Forum Pertama");

    @FXML
    public void initialize() {

        forumTitleLabel.setText(chosenForum.getTitle());
        forumDescLabel.setText(chosenForum.getDescription());
        chatBox.heightProperty().addListener((observable, oldValue, newValue) -> scrollPane.setVvalue(1.0));
    }

    @FXML
    private void sendMessage() {
        String message = messageField.getText();
        if (message.trim().isEmpty()) return;

        if (client != null) {
            System.out.println("client send success");
            client.sendMessage(message);
        }
        this.addMessage(message, true);
        messageField.clear();
    }

    public void receiveMessage(String message) {
        this.addMessage(message, false);
    }

    private void addMessage(String message, boolean isOwnMessage) {
        HBox messageBox = new HBox();
        Text text = new Text(message);
        TextFlow textFlow = new TextFlow(text);

        if (isOwnMessage) {
            messageBox.setStyle("-fx-alignment: center-right;");
            textFlow.setStyle("-fx-background-color: #DDF2FF; -fx-background-radius: 10px; -fx-padding: 5px;");
        } else {
            messageBox.setStyle("-fx-alignment: center-left;");
            textFlow.setStyle("-fx-background-color: #E8E8E8; -fx-background-radius: 10px; -fx-padding: 5px;");
        }

        messageBox.getChildren().add(textFlow);
        chatBox.getChildren().add(messageBox);
    }

    @FXML
    protected void onMyAccountEntered() {
        myAccountBox.setStyle("-fx-background-color: #efefef; -fx-background-radius: 10; -fx-cursor: hand;");
    }

    @FXML
    protected void onMyAccountExited() {
        myAccountBox.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 10; -fx-cursor: default");
    }

    public void setClient(Client client) {
        System.out.println("hi");
        this.client = client;
        if (client != null) System.out.println("client not null");
    }
}