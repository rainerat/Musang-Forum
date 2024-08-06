package com.musang.musang_forum.repository;

import com.musang.musang_forum.model.CurrentForum;
import com.musang.musang_forum.model.Forum;
import com.musang.musang_forum.server.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ForumRepository {

    public static Forum findByTitle(String title) {
        String query = "SELECT * FROM forum WHERE title = ?";

        try (PreparedStatement ps = Database.getInstance().prepareStatement(query)) {
            ps.setString(1, title);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Forum forum = new Forum(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getDate(4), rs.getInt(5));
                CurrentForum.getInstance().set(forum);
                return forum;
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }

        return null;
    }
}
