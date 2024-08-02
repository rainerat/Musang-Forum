package com.musang.musang_forum.model;

import com.musang.musang_forum.server.Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Message {

    private int id;
    private String message;
    private Timestamp time;
    private User user;
    private int forumID;

    public Message(int id, String message, Timestamp date, User user, int forumID) {
        this.id = id;
        this.message = message;
        this.time = date;
        this.user = user;
        this.forumID = forumID;
    }

    public Message(String message, User user, int forumID) {
        this.message = message;
        this.user = user;
        this.forumID = forumID;
    }

    public Message(String message, User user) {
        this.message = message;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getForumID() {
        return forumID;
    }

    public void setForumID(int forumID) {
        this.forumID = forumID;
    }

    @Override
    public String toString() {
        return user.getUsername() + "::" + message;
    }

    public String serialize() {
        return user.getId() + "::" + user.getUsername() + "::" + message;
    }

    public static Message deserialize(String message) {
        String[] parts = message.split("::", 3);
        return new Message(parts[2], new User(Integer.parseInt(parts[0]), parts[1]));
    }

    public void save() {
        String query = "INSERT INTO message (message, time, user_id, forum_id) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = Database.getInstance().prepareStatement(query)) {
            ps.setString(1, message);
            ps.setTimestamp(2, new java.sql.Timestamp(System.currentTimeMillis()));
            ps.setInt(3, user.getId());
            ps.setInt(4, forumID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
