package com.musang.forum.repository;

import com.musang.forum.util.SessionManager;
import com.musang.forum.model.User;
import com.musang.forum.server.Database;
import javafx.collections.FXCollections;

import java.sql.Date;
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
                userList.add(getUser(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    public static User findByUsername(String username) {
        String query = "SELECT * FROM user WHERE username = ?";
        return UserRepository.findByIdentifier(query, username);
    }

    public static User findByEmail(String email) {
        String query = "SELECT * FROM user WHERE email = ?";
        return UserRepository.findByIdentifier(query, email);
    }

    public static String findSaltByUsername(String username) {
        String query = "SELECT salt FROM user WHERE username = ?";
        return UserRepository.findSaltByIdentifier(username, query);
    }

    public static String findSaltByEmail(String email) {
        String query = "SELECT salt FROM user WHERE email = ?";
        return UserRepository.findSaltByIdentifier(email, query);
    }

    public static User login(String identifier, String hash) {
        String query = "SELECT * FROM user WHERE (username = ? OR email = ?) AND hash = ?";

        try (PreparedStatement ps = Database.getInstance().prepareStatement(query)) {
            ps.setString(1, identifier);
            ps.setString(2, identifier);
            ps.setString(3, hash);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return UserRepository.getUser(rs);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updatePassword(String hash, int userID){

        String query = "UPDATE user SET hash=? WHERE id=?";

        try (PreparedStatement ps = Database.getInstance().prepareStatement(query)) {
            ps.setString(1, hash);
            ps.setInt(2, userID);

            ps.executeUpdate();

        }  catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateUserProfile(int userID, String username, String displayName, Date dob, String email){

        String query = "UPDATE user SET username=?, display_name=?, dob=?, email=? WHERE id=?";

        try (PreparedStatement ps = Database.getInstance().prepareStatement(query)) {
            ps.setString(1, username);
            ps.setString(2, displayName);
            ps.setDate(3, dob);
            ps.setString(4, email);
            ps.setInt(5, userID);


            ps.executeUpdate();

        }  catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] getPhotoProfile(int userID){

        String query = "SELECT profile_picture FROM user where id=?";

        try (PreparedStatement ps = Database.getInstance().prepareStatement(query)) {
            ps.setInt(1, userID);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                return rs.getBytes("profile_picture");
            }
            return null;
        }  catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    private static User findByIdentifier(String query, String identifier) {
        try (PreparedStatement ps = Database.getInstance().prepareStatement(query)) {
            ps.setString(1, identifier);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return getUser(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static String findSaltByIdentifier(String username, String query) {
        try (PreparedStatement ps = Database.getInstance().prepareStatement(query)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "";
    }

    private static User getUser(ResultSet rs) throws SQLException {
        return new User(
                rs.getInt("id"), rs.getString("username"), rs.getString("display_name"),
                rs.getDate("dob"), rs.getString("email"), rs.getString("salt"),
                rs.getString("hash"), rs.getBytes("profile_picture")
        );
    }
}
