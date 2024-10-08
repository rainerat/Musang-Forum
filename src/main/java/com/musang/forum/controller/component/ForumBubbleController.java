package com.musang.forum.controller.component;

import com.musang.forum.controller.Controller;
import com.musang.forum.controller.main.ForumController;
import com.musang.forum.model.CurrentForum;
import com.musang.forum.model.Forum;
import com.musang.forum.util.NotificationType;
import com.musang.forum.util.Path;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class ForumBubbleController extends Controller {
    @FXML
    private Label titleLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label totalReplyLabel;

    private Forum forum;

    public ForumBubbleController() {
        super(Path.HOME);
    }

    public void setDiscussion(Forum forum) {
        this.forum = forum;
        titleLabel.setText(this.forum.getTitle());
        descriptionLabel.setText(this.forum.getDescription());
    }

    @FXML
    public void handleForumBubble() throws IOException {
        CurrentForum.getInstance().set(forum);
        ForumController controller = (ForumController) super.loadPage(Path.FORUM);

        try {
            app().getClient().setForumController(controller);
        } catch(NullPointerException e) {
            System.err.println("Client is null");
            super.alert(NotificationType.ERROR, "Can't connect to the server");
        }
    }

}
