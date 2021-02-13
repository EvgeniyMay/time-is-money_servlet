package com.mylearning.timeismoney.dao.impl;

import com.mylearning.timeismoney.dao.MissionDao;
import com.mylearning.timeismoney.dao.mapper.ActivityMapper;
import com.mylearning.timeismoney.dao.mapper.MissionMapper;
import com.mylearning.timeismoney.dao.mapper.UserMapper;
import com.mylearning.timeismoney.entity.Activity;
import com.mylearning.timeismoney.entity.Mission;
import com.mylearning.timeismoney.entity.User;

import java.sql.*;
import java.util.*;

public class JdbcMissionDao implements MissionDao {

    private final Connection connection;
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("database");

    public JdbcMissionDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Mission> findAll() {
        Map<Integer, User> userMap = new HashMap<>();
        Map<Integer, Activity> activityMap = new HashMap<>();
        List<Mission> missions = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(resourceBundle.getString("query.mission.find.all"))) {
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                if(!userMap.containsKey(userId)) {
                    User user = UserMapper.getFromResultSet(resultSet);
                    userMap.put(userId, user);
                }

                int activityId = resultSet.getInt("activity_id");
                if(!activityMap.containsKey(activityId)) {
                    Activity activity = ActivityMapper.getFromResultSet(resultSet);
                    activityMap.put(activityId, activity);
                }

                Mission mission = MissionMapper.getFromResultSet(resultSet);
                mission.setUser(userMap.get(userId));
                mission.setActivity(activityMap.get(activityId));

                missions.add(mission);
            }

            return missions;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
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
