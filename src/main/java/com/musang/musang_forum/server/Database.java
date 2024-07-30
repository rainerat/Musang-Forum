package com.musang.musang_forum.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {

    private static Database instance;
    private final Connection con;

    private Database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/musang", "root", "");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException();
        }
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public PreparedStatement prepareStatement(String query) throws SQLException {
        return con.prepareStatement(query);
    }

}
