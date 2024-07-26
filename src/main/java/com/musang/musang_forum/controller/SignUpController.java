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
import java.sql.Date;
import java.time.LocalDate;

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
    protected void saveUser() {
        User validatedUser = validateUser();
//        if (validatedUser != null) validatedUser.save();
    }

    private User validateUser() {
        String username = usernameField.getText();
        LocalDate date = dobField.getValue();
        String email = emailField.getText();
        String password = passwordField1.getText();
        String confirmPw = passwordField2.getText();

        // Border styles
        String normalFieldStyle = "-fx-background-color: #ffffff; -fx-border-color: acacac; -fx-border-radius: 5; -fx-border-width: 1.2";
        String errorFieldStyle =  "-fx-background-color: #ffffff; -fx-border-color: c04431; -fx-border-radius: 5; -fx-border-width: 1.2";
        String normalDateStyle = "-fx-font-size: 16; -fx-font-family: 'Segoe UI'; -fx-background-color: ffffff; -fx-border-color: acacac; -fx-border-radius: 5; -fx-border-width: 1.2;";
        String errorDateStyle = "-fx-font-size: 16; -fx-font-family: 'Segoe UI'; -fx-background-color: ffffff; -fx-border-color: c04431; -fx-border-radius: 5; -fx-border-width: 1.2;";

        // all text fields must be filled
        if (!isFilled(usernameField, emailField, passwordField1, passwordField2)) {
            usernameField.setStyle(errorFieldStyle);
            emailField.setStyle(errorFieldStyle);
            dobField.setStyle(errorDateStyle);
            passwordField1.setStyle(errorFieldStyle);
            passwordField2.setStyle(errorFieldStyle);
            return null;
        }

        // name can't be less than 4 characters
        if (username.length() < 4) {
            usernameField.setStyle(errorFieldStyle);
            return null;
        }

        // username must be unique
        for (String otherUsername : UserRepository.getAllUsernames()) {
            if (otherUsername.equals(username)) {
                usernameField.setStyle(errorFieldStyle);
                return null;
            }
        }

        // date can't be empty
        Date dob;
        if (date == null) {
            dobField.setStyle(errorDateStyle);
            return null;
        } else {
            dob = Date.valueOf(dobField.getValue());
        }

        // email must be valid
        if (!(email.contains("@") && email.contains(".com"))) {
            // something here
            return null;
        }

        // password can't be less than 6 characters
        if (password.length() < 6) {
            // something here
            return null;
        }

        // passwords must match
        if (!confirmPw.equals(password)) {
            // something here
            return null;
        }

        return new User(username, dob, email, password);
    }

    private boolean isFilled(TextField... fields) {
        for (TextField field : fields) {
            if (field.getText().isEmpty()) {
                return false;
            }
        }
        return true;
    }

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
