package com.musang.forum.service;




import com.musang.forum.server.Database;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritablePixelFormat;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.ByteBuffer;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Base64;
import java.util.zip.DeflaterOutputStream;

public class fileHandlerService {

    private FileChooser fileChooser;

    public File openFileChooser(Stage stage) {

        fileChooser = new FileChooser();
        fileChooser.setTitle("Select profile picture");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));

        return fileChooser.showOpenDialog(stage);
    }

    public static byte[] convertFileToByteArray(File file) throws IOException {
        try (FileInputStream fis = new FileInputStream(file);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }

            return baos.toByteArray();
        }


    }

    public static String convertPngToBlob(String filePath) {
        File file = new File(filePath);
        byte[] fileContent = new byte[(int) file.length()];

        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            fileInputStream.read(fileContent);
        } catch (IOException e) {
            throw new RuntimeException("Error reading file", e);
        }

        return Base64.getEncoder().encodeToString(fileContent);
    }


    public static void savePhoto(String filePath, int userID) {
        String query = "UPDATE user set profile_picture = ? WHERE id =?";
        try (PreparedStatement ps = Database.getInstance().prepareStatement(query)) {


                // Convert PNG to byte array
                File imageFile = new File(filePath);
                FileInputStream inputStream = new FileInputStream(imageFile);

                ps.setBinaryStream(1, inputStream, (int) imageFile.length());
                ps.setInt(2, userID);
                ps.executeUpdate();

                System.out.println("Image saved to database successfully.");

        } catch (SQLException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }




    public static byte[] compressByteSize(byte[] imageFile){


            try {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                DeflaterOutputStream defl = new DeflaterOutputStream(out);
                defl.write(imageFile);
                defl.flush();
                defl.close();

                return out.toByteArray();
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(150);
                return null;
            }

    }


    public static Image convertByteArrayToImage(byte[] imageData) throws IOException {
        if(imageData==null){
            return null;
        };
            return new Image(new ByteArrayInputStream(imageData));

    }

    public static byte[] convertImagetoByteArray(Image image) throws IOException {

        PixelReader pixelReader = image.getPixelReader();
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        WritablePixelFormat<ByteBuffer> format = WritablePixelFormat.getByteBgraInstance();
        byte[] buffer = new byte[width * height * 4]; // 4 bytes per pixel (BGRA)
        pixelReader.getPixels(0, 0, width, height, format, buffer, 0, width * 4);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(buffer);

        return baos.toByteArray();

    }


}
