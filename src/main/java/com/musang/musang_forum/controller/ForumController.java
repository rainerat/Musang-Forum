package com.musang.musang_forum.controller;

import com.musang.musang_forum.Main;
import com.musang.musang_forum.client.Client;
import com.musang.musang_forum.component.MessageComponent;
import com.musang.musang_forum.model.Forum;
import com.musang.musang_forum.model.Message;
import com.musang.musang_forum.repository.ForumRepository;
import com.musang.musang_forum.repository.MessageRepository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ForumController extends Controller {
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

    private Forum chosenForum = ForumRepository.findByTitle("Forum Pertama");

    @FXML
    public void initialize() {
        forumTitleLabel.setText(chosenForum.getTitle());
        forumDescLabel.setText(chosenForum.getDescription());
        chatBox.heightProperty().addListener((observable, oldValue, newValue) -> scrollPane.setVvalue(1.0));

        List<Message> messages = MessageRepository.loadMessages(chosenForum.getId());
        this.displayPreviousMessages(messages);
    }

    @FXML
    private void sendMessage() {
        String message = messageField.getText();
        if (message.trim().isEmpty()) {
            return;
        }

        if (client != null) {
            client.sendMessage(message);
        }

        messageField.clear();
    }

    public void receiveMessage(Message message) {
        this.addMessageToUI(message, false);
    }

    public void addMessageToUI(Message message, boolean isOwnMessage) {
        HBox messageBox;
        if (isOwnMessage) {
            messageBox = MessageComponent.setOwnMessageBox(message.getMessage());
        } else {
            messageBox = MessageComponent.setOthersMessageBox(message.getUser().getUsername(), message.getMessage());
        }
        chatBox.getChildren().add(messageBox);
    }

    public void displayPreviousMessages(List<Message> messageList) {
        for (Message message : messageList) {
            if (message.getUser().getId() == currentUser.get().getId()) {
                chatBox.getChildren().add(MessageComponent.setOwnMessageBox(message.getMessage()));
            } else {
                chatBox.getChildren().add(MessageComponent.setOthersMessageBox(message.getUser().getUsername(), message.getMessage()));
            }
        }
    }

    @FXML
    protected void openMyAccountPage() throws IOException {
        stage = (Stage) sendButton.getScene().getWindow();
        stage.setScene(new Scene(new FXMLLoader(Main.class.getResource("view/MyAccount.fxml")).load()));
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
        super.client = client;
    }
}