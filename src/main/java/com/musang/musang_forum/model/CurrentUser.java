package com.musang.musang_forum.model;

public class CurrentUser {
    private static CurrentUser instance;
    private User currentUser;

    private CurrentUser() {}

    public static CurrentUser getInstance() {
        if (instance == null) {
            instance = new CurrentUser();
        }
        return instance;
    }

    public User get() {
        return currentUser;
    }

    public void set(User user) {
        this.currentUser = user;
    }

    public void clear() {
        this.currentUser = null;
    }
}
