package com.myLearning.timeIsMoney.dao;

import com.myLearning.timeIsMoney.entity.Activity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcActivityDao implements ActivityDao {

    //ToDo
    // Out of here
    private final static String SQL_SELECT_ALL = "SELECT * FROM activity;";
    private final static String SQL_SELECT_BY_ID = "SELECT * FROM activity WHERE id = ?;";
    private final static String SQL_SAVE = "INSERT INTO activity (name, description) VALUES (?, ?);";
    private final static String SQL_DELETE_BY_NAME = "DELETE FROM activity WHERE name = ?;";
    private final static String SQL_UPDATE_QUERY = "UPDATE activity SET name = ?, description = ? WHERE id = ?";

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public List<Activity> findAll() {
        List<Activity> activities = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection();
            Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL)) {

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
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
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
    public boolean save(Activity activity) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE)) {
            preparedStatement.setString(1, activity.getName());
            preparedStatement.setString(2, activity.getDescription());

            return preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public boolean deleteByName(String name) {
        try (Connection connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BY_NAME)) {
            preparedStatement.setString(1, name);

            return preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public boolean update(Activity activity) {
        try (Connection connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_QUERY)) {

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