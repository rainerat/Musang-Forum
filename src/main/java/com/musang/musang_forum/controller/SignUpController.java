package com.musang.musang_forum.controller;

import com.musang.musang_forum.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUpController {

    @FXML
    private TextField usernameField;

    @FXML
    private TextField emailField;

    @FXML
    private DatePicker dobField;

    @FXML
    private PasswordField passwordField1;

    @FXML
    private PasswordField passwordField2;

    @FXML
    private Button submitButton;

    @FXML
    private Label signInLabel;

    @FXML
    protected void openSignInPage() throws IOException {
        Stage stage = (Stage) submitButton.getScene().getWindow();
        stage.setScene(new Scene(new FXMLLoader(Main.class.getResource("view/SignIn.fxml")).load()));
    }

    @FXML
    protected void signInEnter() {
        signInLabel.setStyle("-fx-underline: true; -fx-cursor: hand");
    }

    @FXML
    protected void signInExit() {
        signInLabel.setStyle("-fx-underline: false; -fx-cursor: default");
    }

}
