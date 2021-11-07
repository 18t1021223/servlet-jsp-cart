package com.vn.repository;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConnectSQL {

    private static final String username = "sa";
    private static final String password = "123";
    private static final String url = "jdbc:sqlserver://localhost:1433;databaseName=QlSach";
    private static Connection instance;

    public static Connection getInstance() {
        if (instance == null) {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                ConnectSQL.instance = DriverManager.getConnection(url, username, password);
            } catch (Exception ex) {
                ex.getStackTrace();
            }
        }
        return instance;
    }

    public static void rollBack(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public static void setAutocommit(Connection connection, boolean b) {
        try {
            connection.setAutoCommit(b);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public static void connectClose(Connection connection) {
        try {
            connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public static void commit(Connection connection) {
        try {
            connection.commit();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
