package com.musang.musang_forum.controller.main;

import com.musang.musang_forum.App;
import com.musang.musang_forum.controller.Controller;
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
