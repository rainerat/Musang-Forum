package com.musang.musang_forum;

import com.musang.musang_forum.client.Client;
import com.musang.musang_forum.controller.ForumController;
import com.musang.musang_forum.server.Server;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        stage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("images/musang_logo_bg.png"))));
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/SignUp.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Musang Forum");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}