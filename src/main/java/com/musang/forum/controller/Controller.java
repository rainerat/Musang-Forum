package com.musang.forum.controller;

import com.musang.forum.App;
import com.musang.forum.Main;
import com.musang.forum.service.NotificationService;
import com.musang.forum.util.PageManager;
import com.musang.forum.util.StageManager;
import com.musang.forum.util.ThemeManager;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public abstract class Controller {
    private final App app = Main.MUSANG_APP;
    private Controller controller;

    public Controller(final String PATH) {
        PageManager.setCurrentPage(PATH);
        ThemeManager.setCurrentTheme(ThemeManager.getAll().getFirst());

        System.out.println("Current Page: " + PageManager.getCurrentPagePath());
        System.out.println("Previous Page: " + PageManager.getPreviousPagePath());
    }

    protected FXMLLoader getLoader(final String PATH) throws IOException {
        return new FXMLLoader(Main.class.getResource(PATH));
    }

//    protected Controller loadPage(final String PATH) throws IOException {
//        PageManager.setPreviousPagePath(PageManager.getCurrentPagePath());
//        FXMLLoader loader = getLoader(PATH);
//        StageManager.getPrimaryStage().setScene(new Scene(loader.load()));
//        return loader.getController();
//    }

    /**
     * Loads TOP LEVEL pages (e.g: Home.fxml, Settings.fxml, SignIn.fxml) ; Not for nested FXMLs
     * Wraps the universal root layout (BorderPane) in a StackPane -
     * then adds a static VBox on top of the BorderPane to house notifications.
     * @param PATH - The path of the FXML file to be loaded
     * @return Controller
     */
    protected Controller loadPage(final String PATH) throws IOException {
        PageManager.setPreviousPagePath(PageManager.getCurrentPagePath());
        FXMLLoader loader = this.getLoader(PATH);

        StackPane stackPane = new StackPane();
        stackPane.setAlignment(Pos.BOTTOM_RIGHT);
        BorderPane root = loader.load();
        stackPane.getChildren().addAll(root,
                app().getNotificationService().getNotificationContainer());

        StageManager.getPrimaryStage().setScene(new Scene(stackPane));
        return loader.getController();
    }

    protected Controller loadNestedPage(final String NESTED_PATH, Pane centerPane, Controller mainController) {
        try {
            FXMLLoader loader = getLoader(NESTED_PATH);
            Pane pane = loader.load();
            Controller loadedController = loader.getController();
            loadedController.setMainController(mainController);
            centerPane.getChildren().setAll(pane);
            return loadedController;
        } catch (IOException e) {
            alert(NotificationService.NotificationType.ERROR, "Couldn't load page");
            e.printStackTrace();
        }
        return null;
    }

//    protected void loadPreviousPage() throws IOException {
//        StageManager.getPrimaryStage().setScene(new Scene(getLoader(PageManager.getPreviousPagePath()).load()));
//    }

    protected void setMainController(Controller controller) {
        this.controller = controller;
    }

    protected Controller getController() {
        return controller;
    }

    public void alert(NotificationService.NotificationType type, String message) {
        try {
            app().getNotificationService().send(type, message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected App app() {
        return app;
    }
}
