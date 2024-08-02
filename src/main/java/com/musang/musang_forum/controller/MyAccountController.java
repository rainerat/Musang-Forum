package com.musang.musang_forum.controller;

import com.musang.musang_forum.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class MyAccountController extends Controller {
    @FXML
    private HBox myAccountBox;

    @FXML
    private Button logoutButton;

    @FXML
    protected void handleLogout() throws IOException {
        this.openSignInPage();
    }

    @FXML
    protected void openSignInPage() throws IOException {
        stage = (Stage) myAccountBox.getScene().getWindow();
        stage.setScene(new Scene(new FXMLLoader(Main.class.getResource("view/SignIn.fxml")).load()));
    }

    @FXML
    protected void onMyAccountEntered() {
        myAccountBox.setStyle("-fx-background-color: #efefef; -fx-background-radius: 10; -fx-cursor: hand;");
    }

    @FXML
    protected void onMyAccountExited() {
        myAccountBox.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 10; -fx-cursor: default");
    }
}
