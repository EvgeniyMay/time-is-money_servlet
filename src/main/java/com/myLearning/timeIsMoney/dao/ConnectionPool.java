package com.myLearning.timeIsMoney.dao;


import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {

    //ToDo
    // Make properties
    private static final String URL = "jdbc:mysql://localhost:3306/time_is_money_db?useUnicode=true&serverTimezone=UTC";
    private static final String USER = "myUser";
    private static final String PASSWORD = "myUserPassword";
    private static final String DRIVER = "com.mysql.jdbc.Driver";

    private DataSource dataSource;
    private static volatile ConnectionPool connectionPool;

    private ConnectionPool() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(URL);
        basicDataSource.setUsername(USER);
        basicDataSource.setPassword(PASSWORD);
        basicDataSource.setDriverClassName(DRIVER);

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
