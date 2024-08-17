package com.musang.forum.controller.settings;

import com.musang.forum.controller.Controller;
import com.musang.forum.util.Path;
import javafx.fxml.FXML;

public class UpdatePasswordController extends Controller {

    public UpdatePasswordController() {
        super(Path.SETTINGS);
    }

    @FXML
    protected void handleCancelButton() {
        ((SettingsController) super.getController()).showMyAccountPage();
    }
}
