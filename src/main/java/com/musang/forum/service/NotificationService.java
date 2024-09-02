package com.musang.forum.service;

import com.musang.forum.Main;
import com.musang.forum.controller.component.NotificationController;
import com.musang.forum.util.NotificationType;
import com.musang.forum.util.Path;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

public class NotificationService {

    private static final VBox notificationContainer = new VBox();

    public NotificationService() {
        notificationContainer.setMaxHeight(notificationContainer.getHeight());
        notificationContainer.getStyleClass().add("container");
        notificationContainer.getStylesheets()
                .add(Objects.requireNonNull(Main.class.getResource("styles/notification-style.css"))
                        .toExternalForm());
    }

    /**
     * Sends a notification then removing it after five seconds.
     * @param type - The notification type
     * @param message - The notification message
     */
    public void send(NotificationType type, String message) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(Path.NOTIFICATION_BUBBLE));
        HBox notification = loader.load();
        NotificationController controller = loader.getController();

        switch (type) {
            case INFO:
                notification.getStyleClass().add("info-box");
                controller.setMessage("Info: " + message);
                controller.setInfoIcon();
                break;

            case WARNING:
                notification.getStyleClass().add("warning-box");
                controller.setMessage("Warning: " + message);
                controller.setWarningIcon();
                break;

            case ERROR:
                notification.getStyleClass().add("error-box");
                controller.setMessage("Error: " + message);
                controller.setErrorIcon();
                break;

            case SUCCESS:
                notification.getStyleClass().add("success-box");
                controller.setMessage("Success: " + message);
                controller.setSuccessIcon();
                break;
        }

        notificationContainer.getChildren().add(notification);

        final int FADE_DURATION = 5;
        new Timeline(new KeyFrame(Duration.seconds(FADE_DURATION), event -> {
            this.close(notification);
        })).play();
    }

    /**
     * Removes the notification with a fade animation
     * @param notification - The notification container
     */
    public void close(HBox notification) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(350), notification);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setOnFinished(event -> notificationContainer.getChildren().remove(notification));
        fadeTransition.play();
    }

    public VBox getNotificationContainer() {
        return notificationContainer;
    }
}
