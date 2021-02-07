package com.myLearning.timeIsMoney.dao;

import com.myLearning.timeIsMoney.entity.Activity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcActivityDao implements ActivityDao {

    //ToDo
    // Out of here
    private final static String SQL_SELECT_ALL = "SELECT * FROM activity;";

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public List<Activity> findAll() {
        List<Activity> activities = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection();
            Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL)) {

            while (resultSet.next()) {
                Activity activity = new Activity();

                activity.setName(resultSet.getString("name"));
                activity.setDescription(resultSet.getString("description"));

                activities.add(activity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activities;
    }
}