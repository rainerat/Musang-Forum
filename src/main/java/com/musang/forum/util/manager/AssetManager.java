package com.musang.forum.util.manager;

import com.musang.forum.Main;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.util.Objects;

public class AssetManager {
    public static ImagePattern loadImagePattern(String path) {
        return new ImagePattern(
                new Image(Objects.requireNonNull(
                        Main.class.getResource(path))
                        .toExternalForm()
                ));
    }
}
