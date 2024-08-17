package com.musang.forum.controller.main;

import com.musang.forum.App;
import com.musang.forum.controller.Controller;
import javafx.fxml.FXML;

import java.io.IOException;

public class StartDiscussionController extends Controller {

    public StartDiscussionController() {
        super(App.HOME_PATH);
    }

    @FXML
    protected void handleCancelButton() throws IOException {
        super.loadPreviousPage();
    }

}
