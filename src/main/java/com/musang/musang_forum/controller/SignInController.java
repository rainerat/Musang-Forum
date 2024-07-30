package com.musang.musang_forum.controller;

import com.musang.musang_forum.Main;
import com.musang.musang_forum.client.Client;
import com.musang.musang_forum.repo.UserRepository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class SignInController extends Controller {

    @FXML
    private TextField identifierField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button submitButton;

    @FXML
    private Label signUpLabel;

    @FXML
    private Label errorLabel;

    @FXML
    private void initialize() {
        stage = (Stage) submitButton.getScene().getWindow();
    }


    @FXML
    private void handleSignIn() throws IOException {
        final String errorFieldStyle =  "-fx-background-color: #ffffff; -fx-border-color: c04431; -fx-border-radius: 5; -fx-border-width: 1.2";
        String identifier = identifierField.getText();
        String password = passwordField.getText();
        String salt;

        if (identifier.isBlank()) identifierField.setStyle(errorFieldStyle);
        if (password.isBlank()) passwordField.setStyle(errorFieldStyle);

        if (identifier.contains("@")) {
            salt = UserRepository.getSaltByEmail(identifier);
        } else {
            salt = UserRepository.getSaltByUsername(identifier);
        }

        boolean isValidLogin = UserRepository.login(identifier, encryptionService.getHash(password, salt));
        if (!isValidLogin) {
            errorLabel.setVisible(true);
            identifierField.setStyle(errorFieldStyle);
            passwordField.setStyle(errorFieldStyle);
        } else {
            openForumPage();
        }
    }

    @FXML
    protected void openSignUpPage() throws IOException {
        stage.setScene(new Scene(new FXMLLoader(Main.class.getResource("view/SignUp.fxml")).load()));
    }

    // TODO: start back from here - forum controller don't have access to client
    protected void openForumPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/Forum.fxml"));
        ForumController controller = fxmlLoader.getController();
        Client client = new Client("localhost", 59001, controller);
        controller.setClient(client);

        stage.setScene(new Scene(fxmlLoader.load()));
    }

    @FXML
    protected void signInEnter() {
        signUpLabel.setStyle("-fx-underline: true; -fx-cursor: hand");
    }

    @FXML
    protected void signInExit() {
        signUpLabel.setStyle("-fx-underline: false; -fx-cursor: default");
    }
}
