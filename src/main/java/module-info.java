module com.musang.musang_forum {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;
    requires de.jensd.fx.glyphs.fontawesome;
    requires de.jensd.fx.glyphs.iconsfivetwofive;
    requires de.jensd.fx.glyphs.materialdesignicons;

    opens com.musang.musang_forum to javafx.fxml;
    opens com.musang.musang_forum.controller to javafx.fxml;

    exports com.musang.musang_forum;
    exports com.musang.musang_forum.controller;
    exports com.musang.musang_forum.server;
    exports com.musang.musang_forum.model;
    exports com.musang.musang_forum.service;
    exports com.musang.musang_forum.client;
}