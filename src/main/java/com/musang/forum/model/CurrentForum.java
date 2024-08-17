package com.musang.forum.model;

public class CurrentForum {
    private static CurrentForum instance;
    private Forum currentForum;

    private CurrentForum() {}

    public static CurrentForum getInstance() {
        if (instance == null) {
            instance = new CurrentForum();
        }
        return instance;
    }

    public Forum get() {
        return currentForum;
    }

    public void set(Forum forum) {
        this.currentForum = forum;
    }
}
