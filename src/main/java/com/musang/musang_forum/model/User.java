package com.musang.musang_forum.model;

import com.musang.musang_forum.server.Database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class User {
    private int id;
    private String username;
    private Date dob;
    private String email;
    private String password;

    public User(int id, String name, Date dob, String email, String password) {
        this.id = id;
        this.username = name;
        this.dob = dob;
        this.email = email;
        this.password = password;
    }

    public User(String name, Date dob, String email, String password) {
        this.username = name;
        this.dob = dob;
        this.email = email;
        this.password = password;
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

    public Date getDob() {
        return dob;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void save() {
        String query = "INSERT INTO user (username, dob, email, password) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = Database.getInstance().prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setDate(2, dob);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, password);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
