package com.musang.forum.controller.general;

import com.musang.forum.controller.Controller;
import com.musang.forum.util.Path;
import javafx.fxml.FXML;

import java.io.IOException;

public class PopUpController extends Controller {

    public PopUpController() {
        super(Path.POPUP);
    }

    @FXML
    protected void handleContinueButton() throws IOException {
        super.loadPage(Path.SIGNIN);
    }

}