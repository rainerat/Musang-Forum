package com.musang.musang_forum.controller.general;

import com.musang.musang_forum.App;
import com.musang.musang_forum.Main;
import com.musang.musang_forum.controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;

public class PopUpController extends Controller {

    @FXML
    Button button;

    @FXML
    protected void openSignInPage() throws IOException {
        super.getStage().setScene(new Scene(new FXMLLoader(Main.class.getResource(App.SIGNIN_PATH)).load()));
    }
}
