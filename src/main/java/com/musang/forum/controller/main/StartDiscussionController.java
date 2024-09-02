package com.musang.forum.controller.main;

import com.musang.forum.controller.Controller;
import com.musang.forum.util.NotificationType;
import com.musang.forum.util.manager.PageManager;
import com.musang.forum.util.Path;
import javafx.fxml.FXML;

import java.io.IOException;

public class StartDiscussionController extends Controller {

    public StartDiscussionController() {
        super(Path.HOME);
    }

    @FXML
    protected void handleCreateTopicButton() {
        super.alert(NotificationType.ERROR, "Feature not implemented");
    }

    @FXML
    protected void handleCancelButton() throws IOException {
        super.loadPage(PageManager.getPreviousPagePath());
    }

}
