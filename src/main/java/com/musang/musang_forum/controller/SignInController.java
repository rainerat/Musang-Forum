package com.musang.musang_forum.controller;

import com.musang.musang_forum.Main;
import com.musang.musang_forum.model.User;
import com.musang.musang_forum.repo.UserRepository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class SignInController extends Controller{

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button submitButton;

    @FXML
    private Label signUpLabel;

    @FXML
    private Label errorLabel;

    @FXML
    protected void openSignInPage() throws IOException {
        Stage stage = (Stage) submitButton.getScene().getWindow();
        stage.setScene(new Scene(new FXMLLoader(Main.class.getResource("view/SignUp.fxml")).load()));
    }

    @FXML
    protected void signInEnter() {
        signUpLabel.setStyle("-fx-underline: true; -fx-cursor: hand");
    }

    @FXML
    protected void signInExit() {
        signUpLabel.setStyle("-fx-underline: false; -fx-cursor: default");
    }

    @FXML
    private void validateUser() throws IOException {

        String username = usernameField.getText();
        String password = passwordField.getText();

       for(User correctCredential : UserRepository.getAllUsers()){

            if(username.equals(correctCredential.getName()) && password.equals(correctCredential.getPassword())){
                openForumPage();

            } else {
                errorLabel.setVisible(true);
            }
       }

    }


    protected void openForumPage() throws IOException {
        stage = (Stage) submitButton.getScene().getWindow();
        stage.setScene(new Scene(new FXMLLoader(Main.class.getResource("view/Forum.fxml")).load()));
    }

}
