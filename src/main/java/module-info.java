module com.musang.musang_forum {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens com.musang.musang_forum to javafx.fxml;
    exports com.musang.musang_forum;
    exports com.musang.musang_forum.controller;
    opens com.musang.musang_forum.controller to javafx.fxml;
}