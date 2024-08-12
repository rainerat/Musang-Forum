package com.musang.musang_forum.controller.main;

import com.musang.musang_forum.App;
import com.musang.musang_forum.client.Client;
import com.musang.musang_forum.controller.Controller;
import com.musang.musang_forum.controller.component.MessageBubbleController;
import com.musang.musang_forum.model.Forum;
import com.musang.musang_forum.model.Message;
import com.musang.musang_forum.repository.ForumRepository;
import com.musang.musang_forum.repository.MessageRepository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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

    public ForumController() {
        super(App.FORUM_PATH);
    }

    @FXML
    public void initialize() {
        forumTitleLabel.setText(chosenForum.getTitle());
        forumDescLabel.setText(chosenForum.getDescription());
        chatBox.heightProperty().addListener((observable, oldValue, newValue) -> scrollPane.setVvalue(1.0));
        messageField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                this.handleSendButton();
            }
        });

        List<Message> messages = MessageRepository.loadMessages(chosenForum.getId());
        this.displayPreviousMessages(messages);
    }

    @FXML
    private void handleSendButton() {
        String message = messageField.getText();
        if (message.trim().isEmpty()) {
            return;
        }

        Client client = app().getClient();
        if (client != null) {
            client.sendMessage(message);
        }

        messageField.clear();
    }

    public void receiveMessage(Message message) {
        this.addMessageToUI(message, false);
    }

    public void addMessageToUI(Message message, boolean isOwnMessage) {
        try {
            FXMLLoader loader = super.getLoader(App.MESSAGE_BUBBLE_PATH);
            HBox messageBox = loader.load();
            MessageBubbleController controller = loader.getController();
            controller.setMessage(message.getUser().getUsername(), message.getMessage(), isOwnMessage);

            chatBox.getChildren().add(messageBox);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displayPreviousMessages(List<Message> messageList) {
        for (Message message : messageList) {
            try {
                FXMLLoader loader = super.getLoader(App.MESSAGE_BUBBLE_PATH);
                HBox messageBox = loader.load();
                MessageBubbleController controller = loader.getController();

                if (message.getUser().getId() == app().getCurrentUser().getId()) {
                    controller.setMessage(null, message.getMessage(), true);
                } else {
                    controller.setMessage(message.getUser().getUsername(), message.getMessage(), false);
                }

                chatBox.getChildren().add(messageBox);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    protected void handleMyAccountHBox() throws IOException {
        super.loadPage(App.SETTINGS_PATH);
    }

    @FXML
    protected void onButtonEntered(MouseEvent event) {
        Button button = (Button) event.getSource();
        button.setStyle("-fx-background-color: #e5e5e5; -fx-background-radius: 5;");
    }

    @FXML
    protected void onButtonExited(MouseEvent event) {
        Button button = (Button) event.getSource();
        button.setStyle("-fx-background-color: transparent; -fx-background-radius: 5;");
    }

    @FXML
    protected void onMyAccountEntered() {
        myAccountBox.setStyle("-fx-background-color: #e5e5e5; -fx-background-radius: 10;");
    }

    @FXML
    protected void onMyAccountExited() {
        myAccountBox.setStyle("-fx-background-color: transparent; -fx-background-radius: 10;");
    }
}