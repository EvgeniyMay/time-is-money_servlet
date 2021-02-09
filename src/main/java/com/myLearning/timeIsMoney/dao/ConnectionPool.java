package com.myLearning.timeIsMoney.dao;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionPool {

    private final DataSource dataSource;
    private static volatile ConnectionPool connectionPool;
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("database");

    private ConnectionPool() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(resourceBundle.getString("database.url"));
        basicDataSource.setUsername(resourceBundle.getString("database.user"));
        basicDataSource.setPassword(resourceBundle.getString("database.password"));
        basicDataSource.setDriverClassName(resourceBundle.getString("database.driver"));

        dataSource = basicDataSource;
    }

    static ConnectionPool getInstance () {
        if(connectionPool == null) {
            synchronized (ConnectionPool.class) {
                if(connectionPool == null) {
                    connectionPool = new ConnectionPool();
                }
            }
        }
        return connectionPool;
    }

    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
