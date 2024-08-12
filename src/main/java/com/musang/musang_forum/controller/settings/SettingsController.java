package com.musang.musang_forum.controller.settings;

import com.musang.musang_forum.App;
import com.musang.musang_forum.controller.Controller;
import com.musang.musang_forum.customfx.ToggleGroup;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class SettingsController extends Controller {

    @FXML
    private HBox myAccountBox;

    @FXML
    private Pane centerPane;

    @FXML
    private ToggleButton myAccountButton;

    @FXML
    private ToggleButton personalizeButton;

    @FXML
    private ToggleButton informationButton;

    public SettingsController() {
        super(App.SETTINGS_PATH);
    }

    @FXML
    private void initialize() {
        ToggleGroup toggleGroup = new ToggleGroup();
        myAccountButton.setToggleGroup(toggleGroup);
        personalizeButton.setToggleGroup(toggleGroup);
        informationButton.setToggleGroup(toggleGroup);
        myAccountButton.setSelected(true);
        this.showMyAccountPage();
    }

    @FXML
    public void showMyAccountPage() {
        super.loadNestedPage(App.MYACCOUNT_PATH, centerPane, this);
    }

    @FXML
    public void showChangePasswordPage() {
        super.loadNestedPage(App.CHANGEPW_PATH, centerPane, this);
    }

    @FXML
    private void showPersonalizePage() {
        super.loadNestedPage(App.PERSONALIZE_PATH, centerPane, this);
    }

    @FXML
    private void showInformationPage() {
        super.loadNestedPage(App.INFORMATION_PATH, centerPane, this);
    }

    @FXML
    protected void handleBackButton() throws IOException {
        super.loadPreviousPage();
    }

    @FXML
    protected void onButtonEntered(MouseEvent event) {
        Button button = (Button) event.getSource();
        button.setStyle("-fx-background-color: #eaeaea; -fx-background-radius: 5;");
    }

    @FXML
    protected void onButtonExited(MouseEvent event) {
        Button button = (Button) event.getSource();
        button.setStyle("-fx-background-color: transparent; -fx-background-radius: 5;");
    }

    @FXML
    protected void onMyAccountEntered() {
        myAccountBox.setStyle("-fx-background-color: #efefef; -fx-background-radius: 10;");
    }

    @FXML
    protected void onMyAccountExited() {
        myAccountBox.setStyle("-fx-background-color: #f3f4f7; -fx-background-radius: 10; -fx-cursor: default");
    }

}
