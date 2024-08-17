package com.musang.forum.controller.settings;

import com.musang.forum.App;
import com.musang.forum.controller.Controller;
import com.musang.forum.util.ThemeManager;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class PersonalizeController extends Controller {
    @FXML
    private ComboBox<String> themeComboBox;

    public PersonalizeController() {
        super(App.SETTINGS_PATH);
    }

    @FXML
    private void initialize() {
        themeComboBox.setItems(ThemeManager.getAll());
        themeComboBox.getSelectionModel().select(ThemeManager.getCurrentTheme());
    }
}
