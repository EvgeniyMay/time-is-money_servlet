package com.myLearning.timeIsMoney.dao.impl;

import com.myLearning.timeIsMoney.dao.ActivityDao;
import com.myLearning.timeIsMoney.entity.Activity;

import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class JdbcActivityDao implements ActivityDao {

    private final Connection connection;
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("database");

    public JdbcActivityDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Activity> findAll() {
        throw new RuntimeException();
    }

    @Override
    public Optional<Activity> findById(int id) {
        throw new RuntimeException();
    }

    @Override
    public boolean create(Activity activity) {
        throw new RuntimeException();
    }

    @Override
    public boolean deleteById(int id) {
        throw new RuntimeException();
    }

    @Override
    public boolean update(Activity activity) {
        throw new RuntimeException();
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            //ToDo:
            // Add something
        }
    }
}