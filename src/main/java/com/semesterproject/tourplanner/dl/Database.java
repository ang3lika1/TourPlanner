package com.semesterproject.tourplanner.dl;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    public static Database instance = null;
    protected String connectionString;
    protected Connection connection;

    protected Database() {
        try {
            this.connectionString = "jdbc:postgresql://localhost:5432/postgres";
            this.connection = DriverManager.getConnection(connectionString, "postgres", "mysecretpassword");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Database getInstance() {
        return instance;
    }
    public Connection getConnection() {
        return connection;
    }

    static {
        Database.instance = new Database();
    }
}

