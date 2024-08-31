package com.musang.forum.controller.general;

import com.musang.forum.client.Client;
import com.musang.forum.controller.Controller;
import com.musang.forum.controller.main.AllDiscussionsController;
import com.musang.forum.controller.main.HomeController;
import com.musang.forum.service.EncryptionService;
import com.musang.forum.service.NotificationService;
import com.musang.forum.util.SessionManager;
import com.musang.forum.model.User;
import com.musang.forum.repository.UserRepository;
import com.musang.forum.util.Path;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class SignInController extends Controller {

    @FXML
    private TextField identifierField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label signUpLabel;

    @FXML
    private Label errorLabel;

    public SignInController() {
        super(Path.SIGNIN);
    }

    @FXML
    private void handleSignIn() throws IOException {
        final String errorFieldStyle =  "-fx-background-color: #ffffff; -fx-border-color: c04431; -fx-border-radius: 5; -fx-border-width: 1.2";
        String identifier = identifierField.getText().trim();
        String password = passwordField.getText();
        String salt;

        if (identifier.isBlank()) identifierField.setStyle(errorFieldStyle);
        if (password.isBlank()) passwordField.setStyle(errorFieldStyle);

        if (identifier.contains("@")) {
            salt = UserRepository.findSaltByEmail(identifier);
        } else {
            salt = UserRepository.findSaltByUsername(identifier);
        }

        User loggedInUser = UserRepository.login(
                identifier, EncryptionService.getHash(password, salt)
        );

        boolean isValidLogin = loggedInUser != null;

        if (isValidLogin) {
            SessionManager.setCurrentUser(loggedInUser);
            this.loadHomePage();
        } else {
            errorLabel.setVisible(true);
            identifierField.setStyle(errorFieldStyle);
            passwordField.setStyle(errorFieldStyle);
        }
    }

    @FXML
    protected void loadSignUpPage() throws IOException {
        super.loadPage(Path.SIGNUP);
    }

    private void loadHomePage() throws IOException {
        HomeController homeController = (HomeController) super.loadPage(Path.HOME);
        AllDiscussionsController controller = homeController.getDiscussionsController();

        try {
            Client client = new Client("localhost", 59069, app().getCurrentUser());
            client.setDiscussionsController(controller);
            SessionManager.setClient(client);
        } catch(IOException e) {
            System.err.println("Client can't find server socket");
            super.alert(NotificationService.NotificationType.ERROR,
                    "Can't connect to the server");
        }
    }

//    private void loadForumPage() throws IOException {
//        ForumController controller = (ForumController) super.loadPage(App.FORUM_PATH);
//        Client client = new Client("localhost", 59001, controller, app().getCurrentUser());
//        ClientManager.setClient(client);
//    }

    @FXML
    protected void signInEnter() {
        signUpLabel.setStyle("-fx-underline: true; -fx-cursor: hand");
    }

    @FXML
    protected void signInExit() {
        signUpLabel.setStyle("-fx-underline: false; -fx-cursor: default");
    }

}