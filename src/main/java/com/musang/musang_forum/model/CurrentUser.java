package com.musang.musang_forum.model;

public class CurrentUser {
    private static User currentUser;

    public static User get() {
        return currentUser;
    }

    public static void set(User user) {
        currentUser = user;
    }

    public static void clear() {
        currentUser = null;
    }
}
