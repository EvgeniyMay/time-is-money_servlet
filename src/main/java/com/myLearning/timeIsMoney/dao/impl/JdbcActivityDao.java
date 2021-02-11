package com.myLearning.timeIsMoney.dao.impl;

import com.myLearning.timeIsMoney.dao.ActivityDao;
import com.myLearning.timeIsMoney.entity.Activity;

import java.sql.*;
import java.util.ArrayList;
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
        List<Activity> activities = new ArrayList<>();

        try (Statement statement = connection.createStatement();
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
    public Optional<Activity> findById(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(resourceBundle.getString("query.activity.find.by.id"))) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            Activity activity = new Activity();

            activity.setId(id);
            activity.setName(resultSet.getString("name"));
            activity.setDescription(resultSet.getString("description"));

            return Optional.of(activity);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public boolean create(Activity activity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(resourceBundle.getString("query.activity.insert"))) {
            preparedStatement.setString(1, activity.getName());
            preparedStatement.setString(2, activity.getDescription());

            return preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public boolean deleteById(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(resourceBundle.getString("query.activity.delete"))) {
            preparedStatement.setInt(1, id);

            return preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public boolean update(Activity activity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(resourceBundle.getString("query.activity.update"))) {

            preparedStatement.setString(1, activity.getName());
            preparedStatement.setString(2, activity.getDescription());
            preparedStatement.setInt(3, activity.getId());

            return preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
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