package com.musang.forum.repository;

import com.musang.forum.model.Message;
import com.musang.forum.model.User;
import com.musang.forum.server.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageRepository {
    public static List<Message> loadMessages(int forumID) {
        List<Message> messageList = new ArrayList<>();
        String query =
                "SELECT m.id, message, time, " +
                "u.id AS user_id, u.username, u.dob, u.email " +
                "FROM message m " +
                "JOIN user u ON m.user_id = u.id " +
                "WHERE forum_id = ? " +
                "ORDER BY time ASC ";

        try (PreparedStatement ps = Database.getInstance().prepareStatement(query)) {
            ps.setInt(1, forumID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getDate("dob"),
                        rs.getString("email")
                );
                Message message = new Message(
                        rs.getInt("id"),
                        rs.getString("message"),
                        rs.getTimestamp("time"),
                        user,
                        forumID
                );
                messageList.add(message);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return messageList;
    }
}
