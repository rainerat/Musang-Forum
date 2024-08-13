package com.musang.musang_forum.controller.component;

import com.musang.musang_forum.App;
import com.musang.musang_forum.controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class ForumBubbleController extends Controller{
    @FXML
    private Label titleLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label totalReplyLabel;

    public ForumBubbleController() {
        super(App.HOME_PATH);
    }


    public void setDiscussion(String title, String description){
        titleLabel.setText(title);
        descriptionLabel.setText(description);
    }

    public void handleForumBubble() throws IOException {
        super.loadPage(App.FORUM_PATH);
    }


}
