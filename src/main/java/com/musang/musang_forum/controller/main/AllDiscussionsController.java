package com.musang.musang_forum.controller.main;

import com.musang.musang_forum.App;
import com.musang.musang_forum.controller.Controller;
import com.musang.musang_forum.controller.component.ForumBubbleController;
import com.musang.musang_forum.model.Forum;
import com.musang.musang_forum.repository.ForumRepository;
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

//                controller.setMessage(null, message.getMessage(), true);

                discussionContainer.getChildren().add(forumBubble);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
