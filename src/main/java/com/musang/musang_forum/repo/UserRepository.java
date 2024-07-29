package com.musang.musang_forum.repo;

import com.musang.musang_forum.model.User;
import com.musang.musang_forum.server.Database;
import javafx.collections.FXCollections;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserRepository {

    public static List<User> getAllUsers() {
        String query = "SELECT * FROM user";
        List<User> userList = FXCollections.observableArrayList();

        try (PreparedStatement ps = Database.getInstance().prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                userList.add(
                        new User(rs.getInt(1), rs.getString(2),
                                rs.getDate(3), rs.getString(4), rs.getString(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    public static List<String> getAllUsernames() {
        String query = "SELECT username FROM user";
        List<String> nameList = FXCollections.observableArrayList();

        try (PreparedStatement ps = Database.getInstance().prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                nameList.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nameList;
    }


}
