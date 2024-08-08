package com.musang.musang_forum.controller.main;

import com.musang.musang_forum.App;
import com.musang.musang_forum.Main;
import com.musang.musang_forum.controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class HomeController extends Controller {
    @FXML
    private HBox myAccountBox;

    @FXML
    protected void openMyAccountPage() throws IOException {
        super.getStage().setScene(new Scene(new FXMLLoader(Main.class.getResource(App.SETTINGS_PATH)).load()));
    }

    @FXML
    protected void onButtonEntered(MouseEvent event) {
        Button button = (Button) event.getSource();
        button.setStyle("-fx-background-color: #e5e5e5; -fx-background-radius: 5;");
    }

    @FXML
    protected void onButtonExited(MouseEvent event) {
        Button button = (Button) event.getSource();
        button.setStyle("-fx-background-color: transparent; -fx-background-radius: 5;");
    }

    @FXML
    protected void onMyAccountEntered() {
        myAccountBox.setStyle("-fx-background-color: #e5e5e5; -fx-background-radius: 10;");
    }

    @FXML
    protected void onMyAccountExited() {
        myAccountBox.setStyle("-fx-background-color: transparent; -fx-background-radius: 10;");
    }
}
