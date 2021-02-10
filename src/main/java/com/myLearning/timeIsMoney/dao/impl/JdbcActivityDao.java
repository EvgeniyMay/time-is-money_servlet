package com.myLearning.timeIsMoney.dao.impl;

import com.myLearning.timeIsMoney.dao.ActivityDao;
import com.myLearning.timeIsMoney.dao.ConnectionPool;
import com.myLearning.timeIsMoney.dto.ActivityDto;
import com.myLearning.timeIsMoney.entity.Activity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class JdbcActivityDao implements ActivityDao {

    private final ConnectionPool connectionPool;
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("database");

    public JdbcActivityDao(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public List<Activity> findAll() {
        List<Activity> activities = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(resourceBundle.getString("query.activity.find.all"))) {

            while (resultSet.next()) {
                Activity activity = new Activity();

                activity.setId(resultSet.getInt("id"));
                activity.setName(resultSet.getString("name"));
                activity.setDescription(resultSet.getString("description"));

                activities.add(activity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return activities;
    }

    @Override
    public Activity findById(int id) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(resourceBundle.getString("query.activity.find.by.id"))) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            Activity activity = new Activity();

            activity.setId(id);
            activity.setName(resultSet.getString("name"));
            activity.setDescription(resultSet.getString("description"));

            return activity;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public boolean save(ActivityDto activityDto) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(resourceBundle.getString("query.activity.insert"))) {
            preparedStatement.setString(1, activityDto.getName());
            preparedStatement.setString(2, activityDto.getDescription());

            return preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public boolean deleteById(int id) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(resourceBundle.getString("query.activity.delete"))) {
            preparedStatement.setInt(1, id);

            return preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public boolean update(Activity activity) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(resourceBundle.getString("query.activity.update"))) {

            preparedStatement.setString(1, activity.getName());
            preparedStatement.setString(2, activity.getDescription());
            preparedStatement.setInt(3, activity.getId());

            return preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}