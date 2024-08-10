package com.musang.musang_forum.controller.settings;

import com.musang.musang_forum.App;
import com.musang.musang_forum.controller.Controller;
import com.musang.musang_forum.controller.NestedController;
import javafx.fxml.FXML;

public class UpdatePasswordController extends NestedController {
    private SettingsController settingsController;

    public UpdatePasswordController() {
        super(App.SETTINGS_PATH);
    }

    @FXML
    protected void handleCancelButton() {
        settingsController.showMyAccountPage();
    }

    @Override
    protected void setMainController(Controller mainController) {
        this.settingsController = (SettingsController) mainController;
    }
}
