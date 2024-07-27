package com.musang.musang_forum.controller;

import com.musang.musang_forum.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class PopUpController extends Controller {

    @FXML
    Button button;

    @FXML
    protected void openSignInPage() throws IOException {
        stage = (Stage) button.getScene().getWindow();
        stage.setScene(new Scene(new FXMLLoader(Main.class.getResource("view/SignIn.fxml")).load()));
    }
}
