package com.musang.musang_forum.controller;

import com.musang.musang_forum.Main;
import com.musang.musang_forum.client.Client;
import com.musang.musang_forum.model.Forum;
import com.musang.musang_forum.repo.ForumRepository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;

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
        if (message.trim().isEmpty()) {
            return;
        }

        if (client != null) {
            client.sendMessage(message);
            this.addMessageToUI(message, true);
        }

        messageField.clear();
    }

    public void receiveMessage(String message) {
        this.addMessageToUI(message, false);
    }

    private void addMessageToUI(String message, boolean isOwnMessage) {
        HBox messageBox;

        if (isOwnMessage) {
            messageBox = setOwnMessageBox(message);
        } else {
            messageBox = setOthersMessageBox(message);
        }

        chatBox.getChildren().add(messageBox);
    }

    private HBox setOwnMessageBox(String message) {
        Text text = new Text(message);
        TextFlow textFlow = new TextFlow(text);
        HBox messageBox = new HBox(textFlow);
        text.setStyle("-fx-font-family: 'Segoe UI'; -fx-font-size: 16");
        textFlow.setStyle("-fx-background-color: #DDF2FF; -fx-background-radius: 10px; -fx-padding: 10px; -fx-max-width: 600px;");
        messageBox.setStyle("-fx-alignment: center-right");

        return messageBox;
    }

    private HBox setOthersMessageBox(String message) {
        Text messageText = new Text(message);
        TextFlow messageTf = new TextFlow(messageText);
        messageText.setStyle("-fx-font-family: 'Segoe UI'; -fx-font-size: 16");
        messageTf.setStyle("-fx-background-color: #E8E8E8; -fx-background-radius: 0 0 10 10; -fx-padding: 0 10 10 10");
        messageTf.setMaxWidth(600);
        messageTf.setMinWidth(80);

        Text usernameText = new Text(super.currentUser.get().getUsername());
        TextFlow usernameTf = new TextFlow(usernameText);
        usernameText.setStyle("-fx-font-family: 'Segoe UI Semibold'; -fx-font-size: 16; -fx-text-fill: #8300ff");
        usernameTf.setStyle("-fx-background-color: #E8E8E8; -fx-background-radius: 10 10 0 0; -fx-padding: 10 10 0 10");

        usernameTf.prefWidthProperty().bind(messageTf.widthProperty());

        VBox container = new VBox(usernameTf, messageTf);
        container.setAlignment(Pos.CENTER_LEFT);

        HBox messageBox = new HBox(container);
        messageBox.setAlignment(Pos.CENTER_LEFT);

        return messageBox;
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
        this.client = client;
    }
}