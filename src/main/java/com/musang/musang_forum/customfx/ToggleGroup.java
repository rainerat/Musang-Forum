package com.musang.musang_forum.customfx;

public class ToggleGroup extends javafx.scene.control.ToggleGroup {
    public ToggleGroup() {
        super();
        this.selectedToggleProperty().addListener((observable, oldToggle, newToggle) -> {
            if (newToggle == null) {
                this.selectToggle(oldToggle);
            }
        });
    }

}
