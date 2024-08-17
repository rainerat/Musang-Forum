package com.musang.forum.controller.settings;

import com.musang.forum.App;
import com.musang.forum.client.Client;
import com.musang.forum.controller.Controller;
import com.musang.forum.model.CurrentUser;
import com.musang.forum.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;

public class MyAccountController extends Controller {

    @FXML
    private TextField usernameTf;

    @FXML
    private DatePicker dobTf;

    @FXML
    private TextField displayNameTf;

    @FXML
    private TextField emailTf;

    public MyAccountController() {
        super(App.SETTINGS_PATH);
    }

    @FXML
    private void initialize() {
        User currentUser = app().getCurrentUser();
        usernameTf.setText(currentUser.getUsername());
        String displayName = currentUser.getDisplayName();
        if (displayName != null) displayNameTf.setText(displayName);
        dobTf.setValue(currentUser.getDob().toLocalDate());
        emailTf.setText(currentUser.getEmail());
    }

    @FXML
    protected void handleLogoutButton() throws IOException {
        Client client = app().getClient();
        if (client != null) {
            client.close();
            System.out.println(app().getCurrentUser().getUsername() + " client connection closed");
        }

        CurrentUser.clear();
        super.loadPage(App.SIGNIN_PATH);
    }

    @FXML
    protected void openChangePasswordPage() {
        ((SettingsController) super.getController()).showChangePasswordPage();
    }

}