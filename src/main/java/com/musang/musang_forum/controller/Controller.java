package com.musang.musang_forum.controller;

import com.musang.musang_forum.server.Database;

public abstract class Controller {
    protected Database database = Database.getInstance();
}
