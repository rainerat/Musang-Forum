module com.musang.musang_forum {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;
    requires de.jensd.fx.glyphs.fontawesome;
    requires de.jensd.fx.glyphs.iconsfivetwofive;
    requires de.jensd.fx.glyphs.materialdesignicons;
    requires jdk.jshell;

    opens com.musang.forum to javafx.fxml;
    opens com.musang.forum.controller to javafx.fxml;

    exports com.musang.forum;
    exports com.musang.forum.controller;
    exports com.musang.forum.server;
    exports com.musang.forum.model;
    exports com.musang.forum.service;
    exports com.musang.forum.client;
    exports com.musang.forum.controller.settings;
    opens com.musang.forum.controller.settings to javafx.fxml;
    exports com.musang.forum.controller.main;
    opens com.musang.forum.controller.main to javafx.fxml;
    exports com.musang.forum.controller.general;
    opens com.musang.forum.controller.general to javafx.fxml;
    exports com.musang.forum.controller.component;
    opens com.musang.forum.controller.component to javafx.fxml;
}