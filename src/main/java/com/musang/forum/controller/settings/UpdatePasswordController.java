package com.musang.forum.controller.settings;

import com.musang.forum.App;
import com.musang.forum.controller.Controller;
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
