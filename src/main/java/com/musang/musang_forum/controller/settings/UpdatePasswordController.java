package com.musang.musang_forum.controller.settings;

import com.musang.musang_forum.App;
import com.musang.musang_forum.controller.Controller;
import javafx.fxml.FXML;

public class UpdatePasswordController extends Controller {

    public UpdatePasswordController() {
        super(App.SETTINGS_PATH);
    }

    @FXML
    protected void handleCancelButton() {
        ((SettingsController) super.getController()).showMyAccountPage();
    }
}
