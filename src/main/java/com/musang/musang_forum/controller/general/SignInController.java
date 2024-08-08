package com.musang.musang_forum.controller.general;

import com.musang.musang_forum.App;
import com.musang.musang_forum.Main;
import com.musang.musang_forum.client.Client;
import com.musang.musang_forum.controller.Controller;
import com.musang.musang_forum.controller.main.ForumController;
import com.musang.musang_forum.repository.UserRepository;
import com.musang.musang_forum.util.ClientManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;

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
    private void handleSignIn() throws IOException {
        final String errorFieldStyle =  "-fx-background-color: #ffffff; -fx-border-color: c04431; -fx-border-radius: 5; -fx-border-width: 1.2";
        String identifier = identifierField.getText().trim();
        String password = passwordField.getText();
        String salt;

        if (identifier.isBlank()) identifierField.setStyle(errorFieldStyle);
        if (password.isBlank()) passwordField.setStyle(errorFieldStyle);

        if (identifier.contains("@")) {
            salt = UserRepository.getSaltByEmail(identifier);
        } else {
            salt = UserRepository.getSaltByUsername(identifier);
        }

        boolean isValidLogin = UserRepository.login(identifier, app().getEncryptionService().getHash(password, salt));
        if (!isValidLogin) {
            errorLabel.setVisible(true);
            identifierField.setStyle(errorFieldStyle);
            passwordField.setStyle(errorFieldStyle);
        } else {
            this.openHomePage();
//            this.openForumPage();
        }
    }

    @FXML
    protected void openSignUpPage() throws IOException {
        super.getStage().setScene(new Scene(new FXMLLoader(Main.class.getResource(App.SIGNUP_PATH)).load()));
    }

    private void openHomePage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(App.HOME_PATH));
        super.getStage().setScene(new Scene(fxmlLoader.load()));
    }

    private void openForumPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(App.FORUM_PATH));
        super.getStage().setScene(new Scene(fxmlLoader.load()));

        ForumController controller = fxmlLoader.getController();
        Client client = new Client("localhost", 59001, controller, app().getCurrentUser());
        ClientManager.setClient(client);
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
