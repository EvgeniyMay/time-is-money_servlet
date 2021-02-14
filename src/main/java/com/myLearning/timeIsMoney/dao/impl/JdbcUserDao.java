package com.mylearning.timeismoney.dao.impl;

import com.mylearning.timeismoney.dao.UserDao;
import com.mylearning.timeismoney.dao.mapper.ActivityMapper;
import com.mylearning.timeismoney.dao.mapper.MissionMapper;
import com.mylearning.timeismoney.dao.mapper.UserMapper;
import com.mylearning.timeismoney.entity.Activity;
import com.mylearning.timeismoney.entity.Mission;
import com.mylearning.timeismoney.entity.User;

import java.sql.*;
import java.util.*;

public class JdbcUserDao implements UserDao {

    private final Connection connection;
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("database");

    public JdbcUserDao(Connection connection) {
        this.connection = connection;
    }


    @Override
    public List<User> findAll() {
        Map<Integer, User> userMap = new HashMap<>();
        Map<Integer, Activity> activityMap = new HashMap<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(resourceBundle.getString("query.user.find.all"))) {

            while (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                if(!userMap.containsKey(userId)) {
                   User user = UserMapper.getFromResultSet(resultSet);
                   userMap.put(userId, user);
                }

                if(!Objects.isNull(resultSet.getString("mission_state"))) {
                    Mission mission = MissionMapper.getFromResultSet(resultSet);

                    int activityId = resultSet.getInt("activity_id");
                    if(!activityMap.containsKey(activityId)) {
                        Activity activity = ActivityMapper.getFromResultSet(resultSet);
                        activityMap.put(activityId, activity);
                    }

                    Activity activity = activityMap.get(activityId);
                    activity.getMissions().add(mission);

                    User user = userMap.get(userId);
                    user.getMissions().add(mission);

                    mission.setUser(user);
                    mission.setActivity(activity);
                }
            }

            return new ArrayList<>(userMap.values());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public List<User> findAllProxy() {
        List<User> proxyUsers = new ArrayList<>();

        try(PreparedStatement ps = connection.prepareStatement(resourceBundle.getString("query.user.find.all.proxy"));
            ResultSet resultSet = ps.executeQuery()) {

            while (resultSet.next()) {
                User proxyUser = UserMapper.getProxyFromResultSet(resultSet);
                proxyUsers.add(proxyUser);
            }

            return proxyUsers;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public Optional<User> findById(int id) {
        Map<Integer, Activity> activityMap = new HashMap<>();
        User user = null;

        try (PreparedStatement ps = connection.prepareStatement(resourceBundle.getString("query.user.find.by.id"))) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                if(Objects.isNull(user)) {
                    user = UserMapper.getFromResultSet(resultSet);
                }

                if(!Objects.isNull(resultSet.getString("mission_state"))) {
                    Mission mission = MissionMapper.getFromResultSet(resultSet);

                    int activityId = resultSet.getInt("activity_id");
                    if(!activityMap.containsKey(activityId)) {
                        Activity activity = ActivityMapper.getFromResultSet(resultSet);
                        activityMap.put(activityId, activity);
                    }

                    Activity activity = activityMap.get(activityId);
                    activity.getMissions().add(mission);

                    mission.setUser(user);
                    mission.setActivity(activity);

                    user.getMissions().add(mission);
                }
            }

            return Optional.ofNullable(user);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public Optional<User> findByLogin(String login) {
        Map<Integer, Activity> activityMap = new HashMap<>();
        User user = null;

        try (PreparedStatement ps = connection.prepareStatement(resourceBundle.getString("query.user.find.by.login"))) {
            ps.setString(1, login);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                if(Objects.isNull(user)) {
                    user = UserMapper.getFromResultSet(resultSet);
                }

                if(!Objects.isNull(resultSet.getString("mission_state"))) {
                    Mission mission = MissionMapper.getFromResultSet(resultSet);

                    int activityId = resultSet.getInt("activity_id");
                    if(!activityMap.containsKey(activityId)) {
                        Activity activity = ActivityMapper.getFromResultSet(resultSet);
                        activityMap.put(activityId, activity);
                    }

                    Activity activity = activityMap.get(activityId);
                    activity.getMissions().add(mission);

                    mission.setUser(user);
                    mission.setActivity(activity);

                    user.getMissions().add(mission);
                }
            }

            return Optional.ofNullable(user);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public boolean create(User user) {
        try(PreparedStatement ps = connection.prepareStatement(resourceBundle.getString("query.user.insert"))) {
            UserMapper.basicFillStatement(ps, user);

            return ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public boolean update(User user) {
        try(PreparedStatement ps = connection.prepareStatement(resourceBundle.getString("query.user.update"))) {
            UserMapper.basicFillStatement(ps, user);
            ps.setInt(4, user.getId());

            return ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    //ToDo
    // Delete missions of user
    public boolean delete(User user) {
        try(PreparedStatement ps = connection.prepareStatement(resourceBundle.getString("query.user.delete"))) {
            ps.setInt(1, user.getId());

            return ps.execute();
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
