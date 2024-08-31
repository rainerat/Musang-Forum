package com.musang.forum.controller.component;

import com.musang.forum.controller.Controller;
import com.musang.forum.util.PageManager;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class NotificationController extends Controller {
    @FXML
    private HBox notification;

    @FXML
    private Text notificationText;

    public NotificationController() {
        super(PageManager.getPreviousPagePath());
    }

    public void setMessage(String text) {
        notificationText.setText(text);
    }

    @FXML
    protected void handleCloseButton() {
        app().getNotificationService().close(notification);
    }
}
