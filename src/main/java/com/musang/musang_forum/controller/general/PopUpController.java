package com.musang.musang_forum.controller.general;

import com.musang.musang_forum.App;
import com.musang.musang_forum.controller.Controller;
import javafx.fxml.FXML;

import java.io.IOException;

public class PopUpController extends Controller {

    public PopUpController() {
        super(App.POPUP_PATH);
    }

    @FXML
    protected void handleContinueButton() throws IOException {
        super.loadPage(App.SIGNIN_PATH);
    }

}