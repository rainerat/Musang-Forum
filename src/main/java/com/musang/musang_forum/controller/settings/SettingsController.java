package com.musang.musang_forum.controller.settings;

import com.musang.musang_forum.App;
import com.musang.musang_forum.Main;
import com.musang.musang_forum.controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Objects;

public class SettingsController extends Controller {

    @FXML
    private HBox myAccountBox;

    @FXML
    private Pane centerPane;

    @FXML
    private Button myAccountButton;

    @FXML
    private Button personalizeButton;

    @FXML
    private Button informationButton;

    @FXML
    private Button backButton;

    @FXML
    private Button returnButton;

    @FXML
    private void initialize() {
        this.showMyAccountPage();
    }

    @FXML
    private void showMyAccountPage() {
        this.loadPage(App.MYACCOUNT_PATH);
        this.updateButtonStyles(myAccountButton);
    }

    @FXML
    private void showPersonalizePage() {
        this.loadPage(App.PERSONALIZE_PATH);
        this.updateButtonStyles(personalizeButton);
    }

    @FXML
    private void showInformationPage() {
        this.loadPage(App.INFORMATION_PATH);
        this.updateButtonStyles(informationButton);
    }

    private void loadPage(final String PATH) {
        try {
            Pane newPane = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(PATH)));

            centerPane.getChildren().setAll(newPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateButtonStyles(Button activeButton) {
        myAccountButton.setStyle("-fx-background-color: transparent;");
        personalizeButton.setStyle("-fx-background-color: transparent;");
        informationButton.setStyle("-fx-background-color: transparent;");

        activeButton.setStyle("-fx-background-color: #f3f3f3; -fx-background-radius: 10;");
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
        myAccountBox.setStyle("-fx-background-color: #efefef; -fx-background-radius: 10; -fx-cursor: hand;");
    }

    @FXML
    protected void onMyAccountExited() {
        myAccountBox.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 10; -fx-cursor: default");
    }
}
