package com.musang.musang_forum.repository;

import com.musang.musang_forum.model.CurrentUser;
import com.musang.musang_forum.model.User;
import com.musang.musang_forum.server.Database;
import javafx.collections.FXCollections;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserRepository {

    public static List<User> getAll() {
        String query = "SELECT * FROM user";
        List<User> userList = FXCollections.observableArrayList();

        try (PreparedStatement ps = Database.getInstance().prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                userList.add(new User(
                        rs.getInt("id"), rs.getString("username"), rs.getString("display_name"),
                        rs.getDate("dob"), rs.getString("email"), rs.getString("salt"),
                        rs.getString("hash"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    public static User findByUsername(String username) {
        String query = "SELECT * FROM user WHERE username = ?";

        try (PreparedStatement ps = Database.getInstance().prepareStatement(query)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("id"), rs.getString("username"), rs.getString("display_name"),
                        rs.getDate("dob"), rs.getString("email"), rs.getString("salt"),
                        rs.getString("hash")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }

        return null;
    }

    public static User findByEmail(String email) {
        String query = "SELECT * FROM user WHERE email = ?";

        try (PreparedStatement ps = Database.getInstance().prepareStatement(query)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("id"), rs.getString("username"), rs.getString("display_name"),
                        rs.getDate("dob"), rs.getString("email"), rs.getString("salt"),
                        rs.getString("hash")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public static String getSaltByUsername(String username) {
        String query = "SELECT salt FROM user WHERE username = ?";

        try (PreparedStatement ps = Database.getInstance().prepareStatement(query)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return "";
    }

    public static String getSaltByEmail(String email) {
        String query = "SELECT salt FROM user WHERE email = ?";

        try (PreparedStatement ps = Database.getInstance().prepareStatement(query)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return "";
    }

    public static boolean login(String identifier, String hash) {
        String query = "SELECT * FROM user WHERE (username = ? OR email = ?) AND hash = ?";

        try (PreparedStatement ps = Database.getInstance().prepareStatement(query)) {
            ps.setString(1, identifier);
            ps.setString(2, identifier);
            ps.setString(3, hash);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                CurrentUser.set(
                        new User(
                            rs.getInt("id"), rs.getString("username"), rs.getString("display_name"),
                            rs.getDate("dob"), rs.getString("email"), rs.getString("salt"),
                            rs.getString("hash"))
                        );
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
