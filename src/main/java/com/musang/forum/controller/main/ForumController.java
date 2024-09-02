package com.musang.forum.controller.main;

import com.musang.forum.client.Client;
import com.musang.forum.controller.Controller;
import com.musang.forum.controller.component.MessageBubbleController;
import com.musang.forum.model.CurrentForum;
import com.musang.forum.model.Forum;
import com.musang.forum.model.Message;
import com.musang.forum.repository.MessageRepository;
import com.musang.forum.util.manager.PageManager;
import com.musang.forum.util.Path;
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

    private final Forum chosenForum = CurrentForum.getInstance().get();

    public ForumController() {
        super(Path.FORUM);
    }

    @FXML
    public void initialize() {
        chatBox.heightProperty().addListener((observable, oldValue, newValue) -> scrollPane.setVvalue(1.0));
        messageField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                this.handleSendButton();
            }
        });

        this.setForumDetail();
        this.getMessages();
    }

    private void setForumDetail(){
        forumTitleLabel.setText(chosenForum.getTitle());
        forumDescLabel.setText(chosenForum.getDescription());
    }

    private void getMessages() {
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
            FXMLLoader loader = super.getLoader(Path.MESSAGE_BUBBLE);
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
                FXMLLoader loader = super.getLoader(Path.MESSAGE_BUBBLE);
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
        super.loadPage(Path.SETTINGS);
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

    @FXML
    protected void handleBackButton() throws IOException {
        super.loadPage(PageManager.getPreviousPagePath());
    }
}