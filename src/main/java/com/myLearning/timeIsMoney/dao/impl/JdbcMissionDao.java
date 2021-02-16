package com.mylearning.timeismoney.dao.impl;

import com.mylearning.timeismoney.dao.ActivityDao;
import com.mylearning.timeismoney.dao.DaoFactory;
import com.mylearning.timeismoney.dao.MissionDao;
import com.mylearning.timeismoney.dao.UserDao;
import com.mylearning.timeismoney.dao.mapper.ActivityMapper;
import com.mylearning.timeismoney.dao.mapper.MissionMapper;
import com.mylearning.timeismoney.dao.mapper.UserMapper;
import com.mylearning.timeismoney.dto.UsersAndActivities;
import com.mylearning.timeismoney.entity.Activity;
import com.mylearning.timeismoney.entity.Mission;
import com.mylearning.timeismoney.entity.User;
import com.mylearning.timeismoney.entity.enums.MissionState;

import java.sql.*;
import java.util.*;

public class JdbcMissionDao implements MissionDao {

    private final Connection connection;
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("database");

    public final DaoFactory daoFactory;

    public JdbcMissionDao(Connection connection, DaoFactory daoFactory) {
        this.connection = connection;
        this.daoFactory = daoFactory;
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

    //ToDo
    // GOOGLE
    @Override
    public UsersAndActivities getUsersAndActivities() {
        try(UserDao userDao = daoFactory.createUserDao();
            ActivityDao activityDao = daoFactory.createActivityDao()) {
            UsersAndActivities usersAndActivities = new UsersAndActivities();

            usersAndActivities.setUsers(userDao.findAllProxy());
            usersAndActivities.setActivities(activityDao.findAllProxy());

            return usersAndActivities;
        }
    }

    @Override
    public boolean create(Mission mission) {
        try(PreparedStatement ps = connection.prepareStatement(resourceBundle.getString("query.mission.insert"))) {
            MissionMapper.basicFillStatement(ps, mission);
            
            return ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public boolean update(Mission mission) {
        try (PreparedStatement ps = connection.prepareStatement(resourceBundle.getString("query.mission.update"))) {
            MissionMapper.basicFillStatement(ps, mission);
            ps.setInt(6, mission.getId());

            return ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public boolean updateMissionState(Mission mission, MissionState state) {
        try (PreparedStatement ps = connection.prepareStatement(resourceBundle.getString("query.mission.update.change.state"))) {
            ps.setString(1, state.toString());
            ps.setInt(2, mission.getId());

            return ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public boolean delete(Mission mission) {
        try(PreparedStatement ps = connection.prepareStatement(resourceBundle.getString("query.mission.delete"))) {
            ps.setInt(1, mission.getId());

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
        }
    }
}
