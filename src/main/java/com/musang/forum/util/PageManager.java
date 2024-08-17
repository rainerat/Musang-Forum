package com.musang.forum.util;

public class PageManager {

    private static String currentPagePath;
    private static String previousPagePath;

    public static String getCurrentPagePath() {
        return currentPagePath;
    }

    public static void setCurrentPage(String currentPagePath) {
        PageManager.currentPagePath = currentPagePath;
    }

    public static String getPreviousPagePath() {
        return previousPagePath;
    }

    public static void setPreviousPagePath(String previousPagePath) {
        PageManager.previousPagePath = previousPagePath;
    }
}
