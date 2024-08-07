package com.musang.musang_forum.controller.settings;

import com.musang.musang_forum.App;
import com.musang.musang_forum.Main;
import com.musang.musang_forum.controller.Controller;
import com.musang.musang_forum.model.CurrentUser;
import com.musang.musang_forum.util.ClientManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import java.io.IOException;

public class MyAccountController extends Controller {

    @FXML
    private Label usernameLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private void initialize() {
        usernameLabel.setText(app().getCurrentUser().getUsername());
        emailLabel.setText(app().getCurrentUser().getEmail());
    }

    @FXML
    protected void handleLogout() throws IOException {
        if (app().getClient() != null) {
            app().getClient().close();
            System.out.println(app().getCurrentUser().getUsername() + " client connection closed");
        }

        CurrentUser.clear();
        this.openSignInPage();
    }

    @FXML
    protected void openSignInPage() throws IOException {
        super.getStage().setScene(new Scene(new FXMLLoader(Main.class.getResource(App.SIGNIN_PATH)).load()));
    }
}