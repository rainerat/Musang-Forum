package com.musang.musang_forum.model;

import com.musang.musang_forum.server.Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Message {

    private int id;
    private String message;
    private Timestamp time;
    private int userID;
    private int forumID;

    public Message(int id, String message, Timestamp date, int userID, int forumID) {
        this.id = id;
        this.message = message;
        this.time = date;
        this.userID = userID;
        this.forumID = forumID;
    }

    public Message(String message, int userID, int forumID) {
        this.message = message;
        this.userID = userID;
        this.forumID = forumID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getForumID() {
        return forumID;
    }

    public void setForumID(int forumID) {
        this.forumID = forumID;
    }

    public void save() {
        String query = "INSERT INTO message (message, time, user_id, forum_id) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = Database.getInstance().prepareStatement(query)) {
            ps.setString(1, message);
            ps.setTimestamp(2, new java.sql.Timestamp(System.currentTimeMillis()));
            ps.setInt(3, userID);
            ps.setInt(4, forumID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
