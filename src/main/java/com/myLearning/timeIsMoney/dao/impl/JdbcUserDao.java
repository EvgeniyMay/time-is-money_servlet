package com.myLearning.timeIsMoney.dao.impl;

import com.myLearning.timeIsMoney.dao.UserDao;
import com.myLearning.timeIsMoney.dao.mapper.ActivityMapper;
import com.myLearning.timeIsMoney.dao.mapper.MissionMapper;
import com.myLearning.timeIsMoney.dao.mapper.UserMapper;
import com.myLearning.timeIsMoney.entity.Activity;
import com.myLearning.timeIsMoney.entity.Mission;
import com.myLearning.timeIsMoney.entity.User;

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
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new ArrayList<>(userMap.values());
    }
    @Override
    public Optional<User> findById(int id) {
        try (PreparedStatement ps = connection.prepareStatement(resourceBundle.getString("query.user.find.by.id"))) {

            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();

            Map<Integer, Activity> activityMap = new HashMap<>();
            User user = null;

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
        try (PreparedStatement ps = connection.prepareStatement(resourceBundle.getString("query.user.find.by.login"))) {
            ps.setString(1, login);
            ResultSet resultSet = ps.executeQuery();

            Map<Integer, Activity> activityMap = new HashMap<>();
            User user = null;

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
            UserMapper.fillStatement(ps, user);

            return ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public boolean update(User user) {
        try(PreparedStatement ps = connection.prepareStatement(resourceBundle.getString("query.user.update"))) {
            UserMapper.fillStatement(ps, user);
            ps.setInt(4, user.getId());

            return ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

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
