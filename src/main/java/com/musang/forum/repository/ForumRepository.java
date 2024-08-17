package com.musang.forum.repository;

import com.musang.forum.model.CurrentForum;
import com.musang.forum.model.Forum;
import com.musang.forum.server.Database;
import javafx.collections.FXCollections;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ForumRepository {
    public static List<Forum> getAll() {
        String query = "SELECT * FROM forum";
        List<Forum> forumList = FXCollections.observableArrayList();

        try (PreparedStatement ps = Database.getInstance().prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                forumList.add(getForum(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return forumList;
    }

    public static Forum findByTitle(String title) {
        String query = "SELECT * FROM forum WHERE title = ?";

        try (PreparedStatement ps = Database.getInstance().prepareStatement(query)) {
            ps.setString(1, title);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Forum forum = getForum(rs);
                CurrentForum.getInstance().set(forum);
                return forum;
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }

        return null;
    }

    private static Forum getForum(ResultSet rs) throws SQLException {
        return new Forum(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getDate("date_created"),
                rs.getInt("user_id")
        );
    }
}
