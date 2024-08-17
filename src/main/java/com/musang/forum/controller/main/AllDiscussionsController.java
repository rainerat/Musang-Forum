package com.musang.forum.controller.main;

import com.musang.forum.App;
import com.musang.forum.controller.Controller;
import com.musang.forum.controller.component.ForumBubbleController;
import com.musang.forum.model.Forum;
import com.musang.forum.repository.ForumRepository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class AllDiscussionsController extends Controller {
    @FXML
    private VBox discussionContainer;

    public AllDiscussionsController() {
        super(App.HOME_PATH);
    }

    @FXML
    private void initialize() {
        this.displayAllDiscussions(ForumRepository.getAll());
    }

    public void displayAllDiscussions(List<Forum> forumList) {
        for (Forum forum : forumList) {
            try {
                FXMLLoader loader = super.getLoader(App.FORUM_BUBBLE_PATH);
                VBox forumBubble = loader.load();
                ForumBubbleController controller = loader.getController();
                controller.setDiscussion(forum);
                discussionContainer.getChildren().add(forumBubble);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addNewForum(Forum newForum) {
        try {
            FXMLLoader loader = super.getLoader(App.FORUM_BUBBLE_PATH);
            VBox forumBubble = loader.load();
            ForumBubbleController controller = loader.getController();
            controller.setDiscussion(newForum);
            discussionContainer.getChildren().add(0, forumBubble);  // Add the new forum at the top
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
