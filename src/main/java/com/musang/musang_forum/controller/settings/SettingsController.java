package com.musang.musang_forum.controller.settings;

import com.musang.musang_forum.App;
import com.musang.musang_forum.Main;
import com.musang.musang_forum.controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
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

        toggleGroup.selectedToggleProperty().addListener((observable, oldToggle, newToggle) -> {
            if (newToggle == null) {
                toggleGroup.selectToggle(oldToggle);
            }
        });

        myAccountButton.setSelected(true);
        this.showMyAccountPage();
    }

    @FXML
    public void showMyAccountPage() {
        Controller controller = this.loadNestedPage(App.MYACCOUNT_PATH);
        ((MyAccountController) controller).setMainController(this);
    }

    @FXML
    public void showChangePasswordPage() {
        Controller controller = this.loadNestedPage(App.CHANGEPW_PATH);
        ((UpdatePasswordController) controller).setMainController(this);
    }

    @FXML
    private void showPersonalizePage() {
        this.loadNestedPage(App.PERSONALIZE_PATH);
    }

    @FXML
    private void showInformationPage() {
        this.loadNestedPage(App.INFORMATION_PATH);
    }

    protected Controller loadNestedPage(final String NESTED_PATH) {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(Main.class.getResource(NESTED_PATH)));
            Pane pane = loader.load();
            centerPane.getChildren().setAll(pane);
            return loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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
