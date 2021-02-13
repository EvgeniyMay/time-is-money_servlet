package com.mylearning.timeismoney.dao.impl;

import com.mylearning.timeismoney.dao.MissionDao;
import com.mylearning.timeismoney.entity.Mission;

import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class JdbcMissionDao implements MissionDao {

    private final Connection connection;
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("database");

    public JdbcMissionDao(Connection connection) {
        this.connection = connection;
    }

    private static final String SELECT_ALL = "SELECT * FROM mission";
    private static final String INSERT = "INSERT INTO mission (user_id, activity_id, start_time, end_time, state) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE mission SET user_id = ?, activity_id = ?, start_time = ?, end_time = ?, state = ? where id = ?";
    private static final String FIND_BY_ID = "SELECT * FROM mission WHERE id = ?";

    @Override
    public List<Mission> findAll() {
        throw new RuntimeException();
    }

    @Override
    public boolean create(Mission mission) {
        throw new RuntimeException();
    }

    @Override
    public Optional<Mission> findById(int id) {
        throw new RuntimeException();
    }

    @Override
    public boolean update(Mission mission) {
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
