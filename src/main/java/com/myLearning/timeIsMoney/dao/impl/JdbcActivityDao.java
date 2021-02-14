package com.mylearning.timeismoney.dao.impl;

import com.mylearning.timeismoney.dao.ActivityDao;
import com.mylearning.timeismoney.dao.mapper.ActivityMapper;
import com.mylearning.timeismoney.dao.mapper.MissionMapper;
import com.mylearning.timeismoney.dao.mapper.UserMapper;
import com.mylearning.timeismoney.entity.Activity;
import com.mylearning.timeismoney.entity.Mission;
import com.mylearning.timeismoney.entity.User;

import java.sql.*;
import java.util.*;

public class JdbcActivityDao implements ActivityDao {

    private final Connection connection;
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("database");

    public JdbcActivityDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Activity> findAll() {
        Map<Integer, Activity> activityMap = new HashMap<>();
        Map<Integer, User> userMap = new HashMap<>();

        try (PreparedStatement ps = connection.prepareStatement(resourceBundle.getString("query.activity.find.all"))) {
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                int activityId = resultSet.getInt("activity_id");
                if(!activityMap.containsKey(activityId)) {
                    Activity activity = ActivityMapper.getFromResultSet(resultSet);
                    activityMap.put(activityId, activity);
                }

                if(!Objects.isNull(resultSet.getString("mission_state"))) {
                    Mission mission = MissionMapper.getFromResultSet(resultSet);

                    int userId = resultSet.getInt("user_id");
                    if(!userMap.containsKey(userId)) {
                        User user = UserMapper.getFromResultSet(resultSet);
                        userMap.put(userId, user);
                    }

                    Activity activity = activityMap.get(activityId);
                    mission.setActivity(activity);

                    User user = userMap.get(userId);
                    mission.setUser(user);

                    activity.getMissions().add(mission);
                    user.getMissions().add(mission);
                }
            }

            return new ArrayList<>(activityMap.values());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public List<Activity> findAllProxy() {
        List<Activity> proxyActivities = new ArrayList<>();

        try(PreparedStatement ps = connection.prepareStatement(resourceBundle.getString("query.activity.find.all.proxy"));
            ResultSet resultSet = ps.executeQuery()) {

            while(resultSet.next()) {
                Activity proxyActivity = ActivityMapper.getProxyFromResultSet(resultSet);
                proxyActivities.add(proxyActivity);
            }

            return proxyActivities;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public Optional<Activity> findById(int id) {
        Map<Integer, User> userMap = new HashMap<>();
        Activity activity = null;

        try (PreparedStatement ps = connection.prepareStatement(resourceBundle.getString("query.activity.find.by.id"))) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                if(Objects.isNull(activity)) {
                    activity = ActivityMapper.getFromResultSet(resultSet);
                }

                if(!Objects.isNull(resultSet.getString("mission_state"))) {
                    Mission mission = MissionMapper.getFromResultSet(resultSet);

                    int userId = resultSet.getInt("user_id");
                    if(!userMap.containsKey(userId)) {
                        User user = UserMapper.getFromResultSet(resultSet);
                        userMap.put(userId, user);
                    }

                    mission.setActivity(activity);

                    User user = userMap.get(userId);
                    mission.setUser(user);

                    activity.getMissions().add(mission);
                    user.getMissions().add(mission);
                }
            }

            return Optional.ofNullable(activity);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public boolean create(Activity activity) {
        try (PreparedStatement ps = connection.prepareStatement(resourceBundle.getString("query.activity.insert"))) {
            ActivityMapper.basicFillStatement(ps, activity);

            return ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    //ToDo
    // Delete missions of activity
    @Override
    public boolean deleteById(int id) {
        try (PreparedStatement ps = connection.prepareStatement(resourceBundle.getString("query.activity.delete"))) {
            ps.setInt(1, id);

            return ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public boolean update(Activity activity) {
        try (PreparedStatement ps = connection.prepareStatement(resourceBundle.getString("query.activity.update"))) {
            ActivityMapper.basicFillStatement(ps, activity);
            ps.setInt(3, activity.getId());

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