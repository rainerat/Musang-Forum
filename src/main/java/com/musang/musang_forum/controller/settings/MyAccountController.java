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

    @FXML
    private void initialize() {
        // maybe load the photo here
    }

    @FXML
    protected void handleLogout() throws IOException {
        Client client = app().getClient();
        if (client != null) {
            client.close();
            System.out.println(app().getCurrentUser().getUsername() + " client connection closed");
        }

        CurrentUser.clear();
        this.openSignInPage();
    }

    @FXML
    protected void openChangePasswordPage() {
        if (settingsController != null) {
            settingsController.showChangePasswordPage();
        }
    }

    @FXML
    protected void openSignInPage() throws IOException {
        super.getStage().setScene(new Scene(new FXMLLoader(Main.class.getResource(App.SIGNIN_PATH)).load()));
    }

    public void setSettingsController(SettingsController settingsController) {
        this.settingsController = settingsController;
    }
}