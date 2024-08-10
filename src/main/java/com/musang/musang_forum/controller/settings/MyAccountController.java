package com.musang.musang_forum.controller.settings;

import com.musang.musang_forum.App;
import com.musang.musang_forum.Main;
import com.musang.musang_forum.client.Client;
import com.musang.musang_forum.controller.Controller;
import com.musang.musang_forum.model.CurrentUser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import java.io.IOException;

public class MyAccountController extends Controller {

    private SettingsController settingsController;

    public MyAccountController() {
        super(App.SETTINGS_PATH);
    }

    @FXML
    private void initialize() {
        // maybe load the photo here
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
        if (settingsController != null) {
            settingsController.showChangePasswordPage();
        }
    }

    public void setSettingsController(SettingsController settingsController) {
        this.settingsController = settingsController;
    }
}