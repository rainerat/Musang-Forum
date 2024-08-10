package com.musang.musang_forum.controller;

public abstract class NestedController extends Controller {

    private Controller mainController;

    public NestedController(final String PATH) {
        super(PATH);
    }

    public Controller getMainController() {
        return mainController;
    }

    public void setMainController(Controller mainController) {
        this.mainController = mainController;
    }

}
