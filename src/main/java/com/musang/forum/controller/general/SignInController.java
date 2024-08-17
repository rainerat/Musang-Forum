package com.musang.forum.controller.general;

import com.musang.forum.client.Client;
import com.musang.forum.controller.Controller;
import com.musang.forum.controller.main.AllDiscussionsController;
import com.musang.forum.controller.main.HomeController;
import com.musang.forum.repository.UserRepository;
import com.musang.forum.util.ClientManager;
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
            this.loadHomePage();
//            this.loadForumPage();
        }
    }

    @FXML
    protected void loadSignUpPage() throws IOException {
        super.loadPage(Path.SIGNUP);
    }

    private void loadHomePage() throws IOException {
        HomeController homeController = (HomeController) super.loadPage(Path.HOME);
        AllDiscussionsController controller = homeController.getDiscussionsController();
        Client client = new Client("localhost", 59001, app().getCurrentUser());
        client.setDiscussionsController(controller);
        ClientManager.setClient(client);
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