package com.musang.forum.model;

import com.musang.forum.server.Database;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.io.ByteArrayInputStream;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class User {
    private int id;
    private String username;
    private String displayName;
    private Date dob;
    private String email;
    private String salt;
    private String hash;
    private byte[] profilePicture;

    public User(int id, String username, String displayName, Date dob, String email, String salt, String hash, byte[] profilePicture) {
        this.id = id;
        this.username = username;
        this.displayName = displayName;
        this.dob = dob;
        this.email = email;
        this.salt = salt;
        this.hash = hash;
        this.profilePicture = profilePicture;
    }

    public User(String username, Date dob, String email, String salt, String hash) {
        this.username = username;
        this.dob = dob;
        this.email = email;
        this.salt = salt;
        this.hash = hash;
    }

    public User(int id, String username, Date dob, String email) {
        this.id = id;
        this.username = username;
        this.dob = dob;
        this.email = email;
    }

    public User(int id, String username) {
        this.id = id;
        this.username = username;
    }

    public User(String hash) {
       this.hash = hash;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public LocalDate getDob() {
        return dob.toLocalDate();
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSalt() {
        return salt;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public ImagePattern getProfilePicture() {
        if (profilePicture != null) {
            return new ImagePattern(new Image(new ByteArrayInputStream(profilePicture)));
        } else {
            return null;
        }
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

    public void save() {
        String query = "INSERT INTO user (username, dob, email, salt, hash) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = Database.getInstance().prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setDate(2, dob);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, salt);
            preparedStatement.setString(5, hash);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
