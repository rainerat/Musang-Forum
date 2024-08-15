package com.musang.musang_forum.controller.component;

import com.musang.musang_forum.App;
import com.musang.musang_forum.client.Client;
import com.musang.musang_forum.controller.Controller;
import com.musang.musang_forum.controller.main.ForumController;
import com.musang.musang_forum.model.Forum;
import com.musang.musang_forum.util.ClientManager;
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

    private Forum forum;

    public ForumBubbleController() {
        super(App.HOME_PATH);
    }


    public void setDiscussion(Forum forum){
        this.forum = forum;
        titleLabel.setText(this.forum.getTitle());
        descriptionLabel.setText(this.forum.getDescription());
    }

    @FXML
    public void handleForumBubble() throws IOException {
        Client client = ClientManager.getClient();
        ForumController controller = (ForumController) super.loadPage(App.FORUM_PATH);
        controller.setChosenForum(forum);
        client.setForumController(controller);
    }

}
