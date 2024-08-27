package com.musang.forum.controller.settings;

import com.musang.forum.client.Client;
import com.musang.forum.controller.Controller;
import com.musang.forum.model.CurrentUser;
import com.musang.forum.model.User;
import com.musang.forum.repository.UserRepository;
import com.musang.forum.service.fileHandlerService;
import com.musang.forum.util.Path;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;

public class MyAccountController extends Controller {

    @FXML
    private TextField usernameTf;

    @FXML
    private DatePicker dobTf;

    @FXML
    private TextField displayNameTf;

    @FXML
    private TextField emailTf;

    @FXML
    private Circle profilePicture;


    public MyAccountController() {
        super(Path.SETTINGS);
    }

    @FXML
    private void initialize() throws IOException {
        User currentUser = app().getCurrentUser();
        usernameTf.setText(currentUser.getUsername());
        String displayName = currentUser.getDisplayName();
        if (displayName != null) displayNameTf.setText(displayName);
        dobTf.setValue(currentUser.getDob().toLocalDate());
        emailTf.setText(currentUser.getEmail());

        byte[] image = UserRepository.getPhotoProfile(CurrentUser.get().getId());

        if(image!= null) {
            // debugging output photo
//            File outputFile = new File("C:\\Users\\ASUS\\IdeaProjects\\Musang-Forum\\output2.png");
//            try (FileOutputStream fos = new FileOutputStream(outputFile)) {
//                fos.write(image);
//            }
            Image img = new Image(new ByteArrayInputStream(image));
            System.out.println(img.isError());

            profilePicture.setFill(new ImagePattern(img));
        } else {

            profilePicture.setFill(Color.TRANSPARENT);
        }


    }

    @FXML
    protected void handleLogoutButton() throws IOException {
        Client client = app().getClient();
        if (client != null) {
            client.close();
            System.out.println(app().getCurrentUser().getUsername() + " client connection closed");
        }

        CurrentUser.clear();
        super.loadPage(Path.SIGNIN);
    }

    @FXML
    protected void openChoosePhoto() throws IOException {
        Stage fileChooserStage = new Stage();
        fileHandlerService fhs = new fileHandlerService();

        File file = fhs.openFileChooser(fileChooserStage);

        final long MAX_FILE_SIZE_BYTES = 8 * 1024 * 1024;
        if(file!=null) {
            System.out.println(file.length());
            if(file.length()<MAX_FILE_SIZE_BYTES){
            Image img = new Image(file.toURI().toString());
            profilePicture.setFill(new ImagePattern(img));
            System.out.println(file.getPath());
            fileHandlerService.savePhoto(file.getPath(),CurrentUser.get().getId());
            } else{ System.out.println("File size exceeds 8 MB. Please select a smaller file.");}
        }


    }

    @FXML
    protected void updateProfile() throws IOException {



        String username = usernameTf.getText();
        Date dob =  Date.valueOf(dobTf.getValue());
        String displayName = displayNameTf.getText();
        String email = emailTf.getText();
        ImagePattern ip = (ImagePattern) profilePicture.getFill();
        byte[] userPhoto;
        if(ip!=null) {
            Image image = ip.getImage();
            userPhoto = fileHandlerService.convertImagetoByteArray(image);
            System.out.println(userPhoto.length);
        }
        else{userPhoto=null;}

        UserRepository.updateUserProfile(CurrentUser.get().getId(), username, displayName, dob, email, userPhoto);

    }

    @FXML
    protected void openChangePasswordPage() {
        ((SettingsController) super.getController()).showChangePasswordPage();
    }

}