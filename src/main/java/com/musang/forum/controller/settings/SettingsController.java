package com.musang.forum.controller.settings;

import com.musang.forum.controller.Controller;
import com.musang.forum.customfx.ToggleGroup;
import com.musang.forum.util.PageManager;
import com.musang.forum.util.Path;
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
        super(Path.SETTINGS);
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
        super.loadNestedPage(Path.MY_ACCOUNT, centerPane, this);
    }

    @FXML
    public void showChangePasswordPage() {
        super.loadNestedPage(Path.CHANGE_PASSWORD, centerPane, this);
    }

    @FXML
    private void showPersonalizePage() {
        super.loadNestedPage(Path.PERSONALIZE, centerPane, this);
    }

    @FXML
    private void showInformationPage() {
        super.loadNestedPage(Path.INFORMATION, centerPane, this);
    }

    @FXML
    protected void handleBackButton() throws IOException {
        super.loadPage(PageManager.getPreviousPagePath());
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
