package com.musang.forum.controller.settings;

import com.musang.forum.controller.Controller;
import com.musang.forum.util.manager.AssetManager;
import com.musang.forum.util.manager.SessionManager;
import com.musang.forum.model.User;
import com.musang.forum.service.FileService;
import com.musang.forum.service.UserService;
import com.musang.forum.util.Path;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.*;
import java.sql.Date;
import java.util.Objects;

public class MyAccountController extends Controller {
    @FXML
    private TextField usernameTf;

    @FXML
    private DatePicker dobTf;

    @FXML
    private TextField displayNameTf;

    @FXML
    private TextField emailTf;

    @FXML
    private Circle profilePicture;

    private boolean isProfileChanged = false;

    public MyAccountController() {
        super(Path.SETTINGS);
    }

    @FXML
    private void initialize() {
        User currentUser = app().getCurrentUser();
        usernameTf.setText(currentUser.getUsername());
        displayNameTf.setText(Objects.requireNonNullElse(currentUser.getDisplayName(), ""));
        dobTf.setValue(currentUser.getDob());
        emailTf.setText(currentUser.getEmail());
        profilePicture.setFill(Objects.requireNonNullElse(
                currentUser.getProfilePicture(),
                AssetManager.loadImagePattern("images/default_user_icon.png")
        ));
        this.initListeners();

//        if(image != null) {
//            debugging output photo
//            File outputFile = new File("C:\\Users\\ASUS\\IdeaProjects\\Musang-Forum\\output2.png");
//            try (FileOutputStream fos = new FileOutputStream(outputFile)) {
//                fos.write(image);
//            }
//        }
    }

    // updates the profile changed flag when the text field is edited
    private void initListeners() {
        usernameTf.textProperty().addListener((obs, ov, nv) -> isProfileChanged = true);
        dobTf.valueProperty().addListener((obs, ov, nv) -> isProfileChanged = true);
        displayNameTf.textProperty().addListener((obs, ov, nv) -> isProfileChanged = true);
        emailTf.textProperty().addListener((obs, ov, nv) -> isProfileChanged = true);
    }

    @FXML
    protected void handleChoosePhotoButton() {
        final long MAX_FILE_SIZE = FileService.getMb(7);
        File imageFile = FileService.openFileChooser(new Stage());

        if (imageFile == null)
            return;

        if (imageFile.length() < MAX_FILE_SIZE) {
            byte[] imageBytes = FileService.toByteArray(imageFile);
            app().getCurrentUser().setProfilePicture(imageBytes);
            profilePicture.setFill(app().getCurrentUser().getProfilePicture());
            UserService.updateProfilePicture(imageBytes, app().getCurrentUser().getId());
        } else {
            System.out.println("File size exceeds 7 MB");
        }
    }

    @FXML
    protected void handleSaveButton() {
        if (!isProfileChanged)
            return;

        String username = usernameTf.getText();
        Date dob = Date.valueOf(dobTf.getValue());
        String displayName = displayNameTf.getText();
        String email = emailTf.getText();
        int userId = app().getCurrentUser().getId();

        SessionManager.updateCurrentUser(username, displayName, dob, email);
        UserService.update(username, displayName, dob, email, userId);
        System.out.println("User updated");
        this.isProfileChanged = false;
    }

    @FXML
    protected void handleChangePasswordButton() {
        ((SettingsController) super.getController()).showChangePasswordPage();
    }

    @FXML
    protected void handleLogoutButton() throws IOException {
        SessionManager.close();
        super.loadPage(Path.SIGNIN);
    }
}