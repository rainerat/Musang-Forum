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

public class SignUpController extends Controller {

    @FXML
    private TextField usernameField;

    @FXML
    private Label usernameErrorLabel;

    @FXML
    private TextField emailField;

    @FXML
    private Label emailErrorLabel;

    @FXML
    private DatePicker dobField;

    @FXML
    private Label dobErrorLabel;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label passwordErrorLabel;

    @FXML
    private PasswordField confirmPwField;

    @FXML
    private Label confirmPwErrorLabel;

    @FXML
    private Button submitButton;

    @FXML
    private Label signInLabel;

    @FXML
    protected void saveUser() {
        User validatedUser = validateUser();
//        if (validatedUser != null) validatedUser.save();
    }

    private String normalFieldStyle = "-fx-background-color: #ffffff; -fx-border-color: acacac; -fx-border-radius: 5; -fx-border-width: 1.2";
    private String errorFieldStyle =  "-fx-background-color: #ffffff; -fx-border-color: c04431; -fx-border-radius: 5; -fx-border-width: 1.2";
    private String normalDateStyle = "-fx-font-size: 16; -fx-font-family: 'Segoe UI'; -fx-background-color: ffffff; -fx-border-color: acacac; -fx-border-radius: 5; -fx-border-width: 1.2;";
    private String errorDateStyle = "-fx-font-size: 16; -fx-font-family: 'Segoe UI'; -fx-background-color: ffffff; -fx-border-color: c04431; -fx-border-radius: 5; -fx-border-width: 1.2;";

    private User validateUser() {
        String username = usernameField.getText();
        String email = emailField.getText();
        LocalDate dob = dobField.getValue();
        String password = passwordField.getText();
        String confirmPw = confirmPwField.getText();

        boolean isUsernameValid = validateUsername(username);
        boolean isEmailValid = validateEmail(email);
        boolean isDobValid = validateDob(dob);
        boolean isPasswordValid = validatePassword(password);
        boolean isConfirmPwValid = validateConfirmPw(password, confirmPw);

        if (!(isUsernameValid && isEmailValid && isDobValid && isPasswordValid && isConfirmPwValid)) {
            return null;
        }

        return new User(username, Date.valueOf(dob), email, password);
    }

    private boolean validateUsername(String username) {
        if (username.isBlank()) {
            usernameField.setStyle(errorFieldStyle);
            usernameErrorLabel.setText("This field is required");
            usernameErrorLabel.setVisible(true);
            return false;
        }

        // name can't be less than 4 characters
        if (username.length() < 3) {
            usernameField.setStyle(errorFieldStyle);
            usernameErrorLabel.setText("Username must have at least 3 characters");
            usernameErrorLabel.setVisible(true);
            return false;
        }

        // username must be unique
        for (String otherUsername : UserRepository.getAllUsernames()) {
            if (otherUsername.equals(username)) {
                usernameField.setStyle(errorFieldStyle);
                usernameErrorLabel.setText("Username already taken");
                usernameErrorLabel.setVisible(true);
                return false;
            }
        }

        usernameField.setStyle(normalFieldStyle);
        usernameErrorLabel.setVisible(false);
        return true;
    }

    private boolean validateEmail(String email) {
        if (email.isBlank()) {
            emailField.setStyle(errorFieldStyle);
            emailErrorLabel.setText("This field is required");
            emailErrorLabel.setVisible(true);
            return false;
        }

        // email must be valid
        if (!(email.contains("@") && email.contains(".com"))) {
            emailField.setStyle(errorFieldStyle);
            emailErrorLabel.setText("Invalid email address");
            emailErrorLabel.setVisible(true);
            return false;
        }

        emailField.setStyle(normalFieldStyle);
        emailErrorLabel.setVisible(false);
        return true;
    }

    private boolean validateDob(LocalDate dob) {
        // date can't be empty
        if (dob == null) {
            dobField.setStyle(errorDateStyle);
            dobErrorLabel.setText("This field is required");
            dobErrorLabel.setVisible(true);
            return false;
        }

        dobField.setStyle(normalDateStyle);
        dobErrorLabel.setVisible(false);
        return true;
    }

    private boolean validatePassword(String password) {
        if (password.isBlank()) {
            passwordField.setStyle(errorFieldStyle);
            passwordErrorLabel.setText("This field is required");
            passwordErrorLabel.setVisible(true);
            return false;
        }

        // password can't be less than 6 characters
        if (password.length() < 6) {
            passwordField.setStyle(errorFieldStyle);
            passwordErrorLabel.setText("Password must have at least 6 characters");
            passwordErrorLabel.setVisible(true);
            return false;
        }

        passwordField.setStyle(normalFieldStyle);
        passwordErrorLabel.setVisible(false);
        return true;
    }

    private boolean validateConfirmPw(String password, String confirmPw) {
        if (confirmPw.isBlank()) {
            confirmPwField.setStyle(errorFieldStyle);
            confirmPwErrorLabel.setText("This field is required");
            confirmPwErrorLabel.setVisible(true);
            return false;
        }

        // passwords must match
        if (!confirmPw.equals(password)) {
            confirmPwField.setStyle(errorFieldStyle);
            confirmPwErrorLabel.setText("Passwords do not match");
            confirmPwErrorLabel.setVisible(true);
            return false;
        }

        confirmPwField.setStyle(normalFieldStyle);
        confirmPwErrorLabel.setVisible(false);
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
