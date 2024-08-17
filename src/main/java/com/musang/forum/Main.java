package com.musang.forum;

import com.musang.forum.util.StageManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    public static final App MUSANG_APP = new App();

    @Override
    public void start(Stage stage) throws IOException {
        StageManager.setPrimaryStage(stage);
        stage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("images/musang_logo_bg.png"))));
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/general/SignIn.fxml"));
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