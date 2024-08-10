package com.musang.musang_forum.controller.settings;

import com.musang.musang_forum.App;
import com.musang.musang_forum.controller.NestedController;
import javafx.fxml.FXML;

public class UpdatePasswordController extends NestedController {

    public UpdatePasswordController() {
        super(App.SETTINGS_PATH);
    }

    @FXML
    protected void handleCancelButton() {
        ((SettingsController) super.getMainController()).showMyAccountPage();
    }
}
