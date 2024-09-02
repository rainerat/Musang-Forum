package com.musang.forum.controller.main;

import com.musang.forum.controller.Controller;
import com.musang.forum.customfx.ToggleGroup;
import com.musang.forum.util.manager.PageManager;
import com.musang.forum.util.Path;
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
        super(Path.HOME);
        PageManager.setPreviousPagePath(Path.HOME);
    }

    @FXML
    private void initialize() {
        ToggleGroup toggleGroup = new ToggleGroup();
        allDiscussionButton.setToggleGroup(toggleGroup);
        followingButton.setToggleGroup(toggleGroup);
        tagsButton.setToggleGroup(toggleGroup);
        allDiscussionButton.setSelected(true);
        this.showAllDiscussionsPage();
    }

    @FXML
    protected void handleStartDiscussionButton() {
        super.loadNestedPage(Path.START_DISCUSSION, centerPane, this);
    }

    @FXML
    protected void showAllDiscussionsPage() {
        this.discussionsController = (AllDiscussionsController) super.loadNestedPage(Path.DISCUSSION, centerPane, this);
    }

    @FXML
    protected void handleFollowingButton() {
        super.loadNestedPage(Path.FOLLOWING, centerPane, this);
    }

    @FXML
    protected void handleTagsButton() {
        super.loadNestedPage(Path.TAGS, centerPane, this);
    }

    @FXML
    protected void handleMyAccountHBox() throws IOException {
        super.loadPage(Path.SETTINGS);
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
