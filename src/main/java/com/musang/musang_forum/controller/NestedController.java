package com.musang.musang_forum.controller;

public abstract class NestedController extends Controller {

    public NestedController(final String PATH) {
        super(PATH);
    }

    protected abstract void setMainController(Controller mainController);

}
