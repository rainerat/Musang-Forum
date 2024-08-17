package com.musang.forum.controller.main;

import com.musang.forum.controller.Controller;
import com.musang.forum.util.Path;
import javafx.fxml.FXML;

import java.io.IOException;

public class StartDiscussionController extends Controller {

    public StartDiscussionController() {
        super(Path.HOME);
    }

    @FXML
    protected void handleCancelButton() throws IOException {
        super.loadPreviousPage();
    }

}
