package com.musang.forum.util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ThemeManager {
    private static final ObservableList<String> themeList =
            FXCollections.observableArrayList(
                    "Light",
                    "Dark"
            );

    private static String currentTheme;

    public static ObservableList<String> getAll() {
        return themeList;
    }

    public static String getCurrentTheme() {
        return currentTheme;
    }

    public static void setCurrentTheme(String currentTheme) {
        ThemeManager.currentTheme = currentTheme;
    }
}
