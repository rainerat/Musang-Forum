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

    public SettingsController() {
        super(App.SETTINGS_PATH);
    }

    @FXML
    private void initialize() {
        this.showMyAccountPage();
    }

    @FXML
    public void showMyAccountPage() {
        Controller controller = this.loadNestedPage(App.MYACCOUNT_PATH);
        ((MyAccountController) controller).setMainController(this);
        this.updateButtonStyles(myAccountButton);
    }

    @FXML
    public void showChangePasswordPage() {
        Controller controller = this.loadNestedPage(App.CHANGEPW_PATH);
        ((UpdatePasswordController) controller).setMainController(this);
    }

    @FXML
    private void showPersonalizePage() {
        this.loadNestedPage(App.PERSONALIZE_PATH);
        this.updateButtonStyles(personalizeButton);
    }

    @FXML
    private void showInformationPage() {
        this.loadNestedPage(App.INFORMATION_PATH);
        this.updateButtonStyles(informationButton);
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

    private void updateButtonStyles(Button activeButton) {
        myAccountButton.setStyle("-fx-background-color: transparent;");
        personalizeButton.setStyle("-fx-background-color: transparent;");
        informationButton.setStyle("-fx-background-color: transparent;");
        activeButton.setStyle("-fx-background-color: #eaeaea; -fx-background-radius: 10;");
    }

    @FXML
    protected void handleBackButton() throws IOException {
        super.loadPreviousPage();
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
        myAccountBox.setStyle("-fx-background-color: #efefef; -fx-background-radius: 10;");
    }

    @FXML
    protected void onMyAccountExited() {
        myAccountBox.setStyle("-fx-background-color: #f3f4f7; -fx-background-radius: 10; -fx-cursor: default");
    }

}
