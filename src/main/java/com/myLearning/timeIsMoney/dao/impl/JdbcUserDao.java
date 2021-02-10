package com.myLearning.timeIsMoney.dao.impl;

import com.myLearning.timeIsMoney.dao.ConnectionPool;
import com.myLearning.timeIsMoney.dao.UserDao;
import com.myLearning.timeIsMoney.dto.UserDto;
import com.myLearning.timeIsMoney.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class JdbcUserDao implements UserDao {

    private final ConnectionPool connectionPool;
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("database");

    public JdbcUserDao(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(resourceBundle.getString("query.user.find.all"))) {

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("login"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public Optional<User> findById(int id) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(resourceBundle.getString("query.user.find.by.id"))) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setLogin(resultSet.getString("login"));

            return Optional.of(user);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public Optional<User> findByLogin(String login) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(resourceBundle.getString("query.user.find.by.login"))) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password"));

            return Optional.of(user);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public boolean save(UserDto userDto) {
        try (Connection connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(resourceBundle.getString("query.user.insert"))) {
            preparedStatement.setString(1, userDto.getLogin());
            preparedStatement.setString(2, userDto.getPassword());

            return preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
