package com.solvd.busstation.utils;

import com.solvd.busstation.daoClasses.StationDAOimpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger(StationDAOimpl.class);

    private static ConnectionPool pool;
    private final List<Connection> connectionPool = new ArrayList<>(5);

    private ConnectionPool() {
        Properties props = new Properties(); //Read JDBC driver, url, db name, and password from db.properties file
        FileInputStream in = null;
        try {
            in = new FileInputStream("src/main/resources/db.properties");
            props.load(in);
            in.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage() + ", could not read db properties file");
        }

        try { //Load JDBC driver
            String driver = props.getProperty("jdbc.driver");
            if (driver != null) {
                Class.forName(driver) ;
            }
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage() + ", could not load driver");
        }

        String url = props.getProperty("jdbc.url");
        String username = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");

        for (int i = 0; i < 5; i++) { //Add connections to pool
            try {
                connectionPool.add(DriverManager.getConnection(url, username, password));
            } catch (SQLException e) {
                LOGGER.error(e.getMessage() + "could not add connection to pool");
            }
        }
    }

    public static ConnectionPool getInstance() {
        if (pool == null) {
            pool = new ConnectionPool();
        }
        return pool;
    }

    public synchronized Connection getConnection() {
        try {
            if (connectionPool.isEmpty())
                throw new SQLException("No connections available");
            return connectionPool.remove(connectionPool.size() - 1);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
            pool = new ConnectionPool();
        }
        return connectionPool.remove(connectionPool.size() - 1);
    }

    public synchronized void returnConnection(Connection connection) {
        connectionPool.add(connection);
    }
}