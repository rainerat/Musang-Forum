package com.musang.musang_forum.controller.main;

import com.musang.musang_forum.App;
import com.musang.musang_forum.controller.Controller;
import com.musang.musang_forum.customfx.ToggleGroup;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class HomeController extends Controller {
    @FXML
    private HBox myAccountBox;

    @FXML
    private Pane centerPane;

    @FXML
    private ToggleButton allDiscussionButton;

    @FXML
    private ToggleButton followingButton;

    @FXML
    private ToggleButton tagsButton;

    private AllDiscussionsController discussionsController;

    public HomeController() {
        super(App.HOME_PATH);
    }

    @FXML
    private void initialize() {
        ToggleGroup toggleGroup = new ToggleGroup();
        allDiscussionButton.setToggleGroup(toggleGroup);
        followingButton.setToggleGroup(toggleGroup);
        tagsButton.setToggleGroup(toggleGroup);
        this.showAllDiscussionsPage();
    }

    @FXML
    public void showAllDiscussionsPage() {
        this.discussionsController = (AllDiscussionsController) super.loadNestedPage(App.DISCUSSION_PATH, centerPane, this);
    }

    @FXML
    protected void handleMyAccountHBox() throws IOException {
        super.loadPage(App.SETTINGS_PATH);
    }

    @FXML
    protected void onMyAccountEntered() {
        myAccountBox.setStyle("-fx-background-color: #e5e5e5; -fx-background-radius: 10;");
    }

    @FXML
    protected void onMyAccountExited() {
        myAccountBox.setStyle("-fx-background-color: transparent; -fx-background-radius: 10;");
    }

    public AllDiscussionsController getDiscussionsController() {
        return discussionsController;
    }

}
