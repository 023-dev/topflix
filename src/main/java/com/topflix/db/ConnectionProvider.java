package com.topflix.db;

import java.sql.*;


public class ConnectionProvider {
    private static final String URL = "jdbc:mysql://localhost:3306/TOPFLIX";
    private static final String USER = "topgoal";
    private static final String PASSWORD = "topgoal";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC driver not found", e);
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void close(ResultSet rs, Statement stmt, Connection conn) {
        try {
            if(rs != null) {
                rs.close();
            }
            if(stmt != null) {
                stmt.close();
            }
            if(conn != null) {
                conn.close();
            }
        }catch (Exception e) {
            System.out.println("예외발생:"+e.getMessage());
        }
    }
    public static void close(Statement stmt, Connection conn) {
        try {
            if(stmt != null) {
                stmt.close();
            }
            if(conn != null) {
                conn.close();
            }
        }catch (Exception e) {
            System.out.println("예외발생:"+e.getMessage());
        }
    }
}

