package com.musang.forum.controller.settings;

import com.musang.forum.controller.Controller;
import com.musang.forum.service.EncryptionService;
import com.musang.forum.util.SessionManager;
import com.musang.forum.model.User;
import com.musang.forum.repository.UserRepository;
import com.musang.forum.util.Path;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

public class UpdatePasswordController extends Controller {
    @FXML
    private PasswordField currentPasswordTF;

    @FXML
    private Label currentPasswordErrorLabel;

    @FXML
    private PasswordField newPasswordTF;

    @FXML
    Label newPasswordErrorLabel;

    @FXML
    private PasswordField confirmNewPaswordTF;

    @FXML
    Label confirmPasswordErrorLabel;

    private final String normalFieldStyle = "-fx-background-color: #ffffff; -fx-border-color: acacac; -fx-border-radius: 5; -fx-border-width: 1.2";
    private final String errorFieldStyle =  "-fx-background-color: #ffffff; -fx-border-color: c04431; -fx-border-radius: 5; -fx-border-width: 1.2";


    public UpdatePasswordController() {
        super(Path.SETTINGS);
    }

    @FXML
    protected void handleCancelButton() {
        ((SettingsController) super.getController()).showMyAccountPage();
    }

    @FXML
    protected void handleSavePassword(){

        String currentPassword = currentPasswordTF.getText();
        String newPassword = newPasswordTF.getText();
        String confirmPassword = confirmNewPaswordTF.getText();

        boolean isCurrentPasswordValid = validateCurrentPassword(currentPassword);
        boolean isNewPasswordValid = validateNewPassword(newPassword);
        boolean isConfirmPasswordValid = validateConfirmPw(newPassword, confirmPassword);

        System.out.println(isCurrentPasswordValid);
        System.out.println(isNewPasswordValid);
        System.out.println(isConfirmPasswordValid);
        if(isCurrentPasswordValid && isNewPasswordValid && isConfirmPasswordValid)
        {
            String salt = app().getCurrentUser().getSalt();
            String hash = EncryptionService.getHash(newPassword, salt);
            new User(hash);
            UserRepository.updatePassword(hash, app().getCurrentUser().getId());
        }

    }

    private boolean validateCurrentPassword(String currentPassword){
        String salt = app().getCurrentUser().getSalt();
        String hash = EncryptionService.getHash(currentPassword, salt);

        if (currentPassword.isBlank()) {
            currentPasswordTF.setStyle(errorFieldStyle);
            currentPasswordErrorLabel.setText("This field is required");
            currentPasswordErrorLabel.setVisible(true);
            return false;
        }

       if(!hash.equals(app().getCurrentUser().getHash())){
           currentPasswordTF.setStyle(errorFieldStyle);
           currentPasswordErrorLabel.setText("Password doesn't match");
           currentPasswordErrorLabel.setVisible(true);
       }

       currentPasswordTF.setStyle(normalFieldStyle);
       currentPasswordErrorLabel.setVisible(false);
      return true;

    }


    private boolean validateNewPassword(String newPassword) {
        if (newPassword.isBlank()) {
            newPasswordTF.setStyle(errorFieldStyle);
            newPasswordErrorLabel.setText("This field is required");
            newPasswordErrorLabel.setVisible(true);
            return false;
        }

        if (newPassword.length() < 6) {
            newPasswordTF.setStyle(errorFieldStyle);
            newPasswordErrorLabel.setText("Password must be at least 6 characters long");
            newPasswordErrorLabel.setVisible(true);
            return false;
        }

        if (newPassword.contains(" ")) {
            newPasswordTF.setStyle(errorFieldStyle);
            newPasswordErrorLabel.setText("Password can't contain spaces.");
            newPasswordErrorLabel.setVisible(true);
            return false;
        }

        if (newPassword.equalsIgnoreCase(app().getCurrentUser().getUsername())) {
            newPasswordTF.setStyle(errorFieldStyle);
            newPasswordErrorLabel.setText("Password is too weak");
            newPasswordErrorLabel.setVisible(true);
            return false;
        }

        if (!newPassword.matches(".*[A-Z].*") || !newPassword.matches(".*[a-z].*") || !newPassword.matches(".*\\d.*")) {
            newPasswordTF.setStyle(errorFieldStyle);
            newPasswordErrorLabel.setText("Must contain at least one uppercase and lowercase letter and one number");
            newPasswordErrorLabel.setVisible(true);
            return false;
        }

        newPasswordTF.setStyle(normalFieldStyle);
        newPasswordErrorLabel.setVisible(false);
        return true;
    }

    private boolean validateConfirmPw(String newPassword, String newConfirmPassword) {
        if (newConfirmPassword.isBlank()) {
            confirmNewPaswordTF.setStyle(errorFieldStyle);
            confirmPasswordErrorLabel.setText("This field is required");
            confirmPasswordErrorLabel.setVisible(true);
            return false;
        }

        if (!newConfirmPassword.equals(newPassword)) {
            confirmNewPaswordTF.setStyle(errorFieldStyle);
            confirmPasswordErrorLabel.setText("Passwords do not match");
            confirmPasswordErrorLabel.setVisible(true);
            return false;
        }

        confirmNewPaswordTF.setStyle(normalFieldStyle);
        confirmPasswordErrorLabel.setVisible(false);
        return true;
    }

}
