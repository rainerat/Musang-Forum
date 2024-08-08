package com.musang.musang_forum.controller.general;

import com.musang.musang_forum.App;
import com.musang.musang_forum.Main;
import com.musang.musang_forum.controller.Controller;
import com.musang.musang_forum.model.User;
import com.musang.musang_forum.repository.UserRepository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;

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
    private Label signInLabel;

    @FXML
    protected void saveUser() throws IOException {
        User user = validateUser();

        if (user != null) {
            user.save();
            this.openOpenPopUpPage();
        }
    }

    private final String normalFieldStyle = "-fx-background-color: #ffffff; -fx-border-color: acacac; -fx-border-radius: 5; -fx-border-width: 1.2";
    private final String errorFieldStyle =  "-fx-background-color: #ffffff; -fx-border-color: c04431; -fx-border-radius: 5; -fx-border-width: 1.2";

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

        String salt = app().getEncryptionService().nextSalt();
        String hash = app().getEncryptionService().getHash(password, salt);

        return new User(username, Date.valueOf(dob), email, salt, hash);
    }

    private boolean validateUsername(String username) {
        if (username.isBlank()) {
            usernameField.setStyle(errorFieldStyle);
            usernameErrorLabel.setText("This field is required");
            usernameErrorLabel.setVisible(true);
            return false;
        }

        if (username.length() < 3 || username.length() > 20) {
            usernameField.setStyle(errorFieldStyle);
            usernameErrorLabel.setText("Username must be between 3 and 20 characters.");
            usernameErrorLabel.setVisible(true);
            return false;
        }

        if (!username.matches("^[\\w.-]+(?<!\\.)$")) {
            usernameField.setStyle(errorFieldStyle);
            usernameErrorLabel.setText("Username can only contain letters, numbers, and symbols (' . ', ' - ', ' _ ')");
            usernameErrorLabel.setVisible(true);
            return false;
        }

        if (UserRepository.findByUsername(username) != null) {
            usernameField.setStyle(errorFieldStyle);
            usernameErrorLabel.setText("Username already taken");
            usernameErrorLabel.setVisible(true);
            return false;
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

        if (!email.contains("@") || !(email.endsWith(".com") || email.endsWith(".COM"))) {
            emailField.setStyle(errorFieldStyle);
            emailErrorLabel.setText("Invalid email address. Example: name@example.com.");
            emailErrorLabel.setVisible(true);
            return false;
        }

        if (UserRepository.findByEmail(email) != null) {
            emailField.setStyle(errorFieldStyle);
            emailErrorLabel.setText("Email is already in use");
            emailErrorLabel.setVisible(true);
            return false;
        }

        emailField.setStyle(normalFieldStyle);
        emailErrorLabel.setVisible(false);
        return true;
    }

    private boolean validateDob(LocalDate dob) {
        if (dob == null) {
            dobField.setStyle("-fx-font-size: 16; -fx-font-family: 'Segoe UI'; -fx-background-color: ffffff; -fx-border-color: c04431; -fx-border-radius: 5; -fx-border-width: 1.2;");
            dobErrorLabel.setText("This field is required");
            dobErrorLabel.setVisible(true);
            return false;
        }

        dobField.setStyle("-fx-font-size: 16; -fx-font-family: 'Segoe UI'; -fx-background-color: ffffff; -fx-border-color: acacac; -fx-border-radius: 5; -fx-border-width: 1.2;");
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

        if (password.length() < 6) {
            passwordField.setStyle(errorFieldStyle);
            passwordErrorLabel.setText("Password must be at least 6 characters long");
            passwordErrorLabel.setVisible(true);
            return false;
        }

        if (password.contains(" ")) {
            passwordField.setStyle(errorFieldStyle);
            passwordErrorLabel.setText("Password can't contain spaces.");
            passwordErrorLabel.setVisible(true);
            return false;
        }

        if (password.equalsIgnoreCase(usernameField.getText())) {
            passwordField.setStyle(errorFieldStyle);
            passwordErrorLabel.setText("Password is too weak");
            passwordErrorLabel.setVisible(true);
            return false;
        }

        if (!password.matches(".*[A-Z].*") || !password.matches(".*[a-z].*") || !password.matches(".*\\d.*")) {
            passwordField.setStyle(errorFieldStyle);
            passwordErrorLabel.setText("Must contain at least one uppercase and lowercase letter and one number");
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

    private void openOpenPopUpPage() throws IOException {
        super.getStage().setScene(new Scene(new FXMLLoader(Main.class.getResource(App.POPUP_PATH)).load()));
    }

    @FXML
    protected void openSignInPage() throws IOException {
        super.getStage().setScene(new Scene(new FXMLLoader(Main.class.getResource(App.SIGNIN_PATH)).load()));
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
