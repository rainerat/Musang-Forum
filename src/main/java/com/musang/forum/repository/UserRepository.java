package com.musang.forum.repository;

import com.musang.forum.model.CurrentUser;
import com.musang.forum.model.User;
import com.musang.forum.server.Database;
import javafx.collections.FXCollections;

import java.io.ByteArrayInputStream;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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


    public static void updateUserProfile(int userID, String username, String displayName, Date dob, String email, byte[] userProfilePicture){

        String query = "UPDATE user SET username=?, display_name=?, dob=?, email=?, profile_picture=? WHERE id=?";

        try (PreparedStatement ps = Database.getInstance().prepareStatement(query)) {
            ps.setString(1, username);
            ps.setString(2, displayName);
            ps.setDate(3, dob);
            ps.setString(4, email);
            ps.setBytes(5, userProfilePicture);
            ps.setInt(6, userID);


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
}
