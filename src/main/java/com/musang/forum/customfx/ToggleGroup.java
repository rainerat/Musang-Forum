package com.musang.forum.customfx;

public class ToggleGroup extends javafx.scene.control.ToggleGroup {

    /*
     * Toggle group cannot be deactivated if clicked twice.
     */
    public ToggleGroup() {
        super();
        this.selectedToggleProperty().addListener((observable, oldToggle, newToggle) -> {
            if (newToggle == null) {
                this.selectToggle(oldToggle);
            }
        });
    }

}
