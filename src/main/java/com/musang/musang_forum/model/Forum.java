package com.musang.musang_forum.model;

import java.sql.Date;

public class Forum {
    private int id;
    private String title;
    private String description;
    private Date dateCreated;
    private int userID;

    public Forum(int id, String title, String description, Date dateCreated, int userID) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dateCreated = dateCreated;
        this.userID = userID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String serialize() {
        return id + "::" + title + "::" + description + "::" + dateCreated + "::" + userID;
    }

    public static Forum deserialize(String serializedForum) {
        String[] parts = serializedForum.split("::", 5);
        return new Forum(Integer.parseInt(parts[0]), parts[1], parts[2], Date.valueOf(parts[3]), Integer.parseInt(parts[4]));
    }
}
