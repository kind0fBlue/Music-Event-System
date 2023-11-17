package com.unimelb.swen90007.sda1_musicsystem;

import java.sql.*;

public class JDBCtest {
    private final static String url = "jdbc:postgresql://localhost:5432/myDB";
    private final static String user = "postgres";
    private final static String password = "0314";
    /**
     * Connect to the PostgreSQL database
     * @return a Connection object
     */
    public void connectTest() {
        String sql = "SELECT * FROM venues;";
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            conn = DriverManager.getConnection(url, user, password);
            findStatement = conn.prepareStatement(sql);
            findStatement.execute();
            rs = findStatement.getResultSet();
            rs.next();
            String venuename = rs.getString(1);
            System.out.println(venuename);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection connect() {
        String url = "jdbc:postgresql://localhost:5432/myDB";
        String user = "postgres";
        String password = "0314";

        PreparedStatement findStatement = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static Connection connectRender() {
        String user = "postgres01";
        String password = "4h09uFu3MUcAZSusWJpXYbva2G46WkKJ";

        Connection dbConnection =null;
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            String DB_CONNECTION = "jdbc:postgresql://dpg-cjg319337aks73ad1em0-a.oregon-postgres.render.com:5432/mydb_t9d6";
            dbConnection = DriverManager.getConnection(DB_CONNECTION, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dbConnection;
    }



    public static void main(String[] args) {
        JDBCtest app = new JDBCtest();
        app.connectTest();
    }
}