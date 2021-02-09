package com.myLearning.timeIsMoney.dao.impl;

import com.myLearning.timeIsMoney.dao.ConnectionPool;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class JdbcConnectionPool implements ConnectionPool {

    private final DataSource dataSource;
    private static volatile JdbcConnectionPool jdbcConnectionPool;
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("database");

    private JdbcConnectionPool() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(resourceBundle.getString("database.url"));
        basicDataSource.setUsername(resourceBundle.getString("database.user"));
        basicDataSource.setPassword(resourceBundle.getString("database.password"));
        basicDataSource.setDriverClassName(resourceBundle.getString("database.driver"));

        dataSource = basicDataSource;
    }

    public static JdbcConnectionPool getInstance () {
        if(jdbcConnectionPool == null) {
            synchronized (JdbcConnectionPool.class) {
                if(jdbcConnectionPool == null) {
                    jdbcConnectionPool = new JdbcConnectionPool();
                }
            }
        }
        return jdbcConnectionPool;
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
