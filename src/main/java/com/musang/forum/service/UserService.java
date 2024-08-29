package com.musang.forum.service;

import com.musang.forum.server.Database;

import java.io.ByteArrayInputStream;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserService {
    public static void updateProfilePicture(byte[] imageBytes, int userId) {
        String query = "UPDATE user SET profile_picture = ? WHERE id =?";

        try (PreparedStatement ps = Database.getInstance().prepareStatement(query)) {
            ps.setBinaryStream(1, new ByteArrayInputStream(imageBytes), imageBytes.length);
            ps.setInt(2, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void update(String username, String displayName, Date dob, String email, int userId) {
        String query = "UPDATE user SET username = ?, display_name = ?, dob = ?, email = ? WHERE id = ?";

        try (PreparedStatement ps = Database.getInstance().prepareStatement(query)) {
            ps.setString(1, username);
            ps.setString(2, displayName);
            ps.setDate(3, dob);
            ps.setString(4, email);
            ps.setInt(5, userId);
            ps.executeUpdate();
        }  catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
