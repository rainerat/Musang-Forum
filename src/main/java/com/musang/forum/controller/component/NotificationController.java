package com.musang.forum.controller.component;

import com.musang.forum.controller.Controller;
import com.musang.forum.util.manager.PageManager;
import de.jensd.fx.glyphs.icons525.Icons525;
import de.jensd.fx.glyphs.icons525.Icons525View;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class NotificationController extends Controller {
    @FXML
    private HBox notification;

    @FXML
    private Icons525View icon;

    @FXML
    private Text notificationText;

    public NotificationController() {
        super(PageManager.getPreviousPagePath());
    }

    @FXML
    protected void initialize() {
        notificationText.setWrappingWidth(notification.getMinWidth() - 118);
    }

    public void setMessage(String text) {
        notificationText.setText(text);
    }

    public void setInfoIcon() {
        icon.setIcon(Icons525.INFO_CIRCLE);
    }

    public void setWarningIcon() {
        icon.setIcon(Icons525.WARNING_SIGN);
    }

    public void setErrorIcon() {
        icon.setIcon(Icons525.NOENTRY);
    }

    public void setSuccessIcon() {
        icon.setIcon(Icons525.OK);
    }

    @FXML
    protected void handleCloseButton() {
        app().getNotificationService().close(notification);
    }
}
