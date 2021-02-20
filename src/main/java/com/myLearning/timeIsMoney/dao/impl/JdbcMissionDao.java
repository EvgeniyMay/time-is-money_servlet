package com.mylearning.timeismoney.dao.impl;

import com.mylearning.timeismoney.dao.MissionDao;
import com.mylearning.timeismoney.dao.mapper.ActivityMapper;
import com.mylearning.timeismoney.dao.mapper.MissionMapper;
import com.mylearning.timeismoney.dao.mapper.UserMapper;
import com.mylearning.timeismoney.dto.UsersAndActivities;
import com.mylearning.timeismoney.entity.Activity;
import com.mylearning.timeismoney.entity.Mission;
import com.mylearning.timeismoney.entity.User;
import com.mylearning.timeismoney.entity.enums.MissionField;
import com.mylearning.timeismoney.entity.enums.MissionState;

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

        try (PreparedStatement ps = connection.prepareStatement(resourceBundle.getString("query.mission.find.all"));
             ResultSet resultSet = ps.executeQuery();) {

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
    public UsersAndActivities getUsersAndActivities() {
        Map<Integer, User> userMap = new HashMap<>();
        Map<Integer, Activity> activityMap = new HashMap<>();

        try(PreparedStatement ps = connection.prepareStatement(resourceBundle.getString("query.mission.find.info"));
            ResultSet resultSet = ps.executeQuery()) {
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
            }

            UsersAndActivities usersAndActivities = new UsersAndActivities();
            usersAndActivities.setUsers(new ArrayList<>(userMap.values()));
            usersAndActivities.setActivities(new ArrayList<>(activityMap.values()));

            return usersAndActivities;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public List<Mission> findPageableSortedBy(int page, int size, MissionState state, MissionField field) {
        Map<Integer, User> userMap = new HashMap<>();
        Map<Integer, Activity> activityMap = new HashMap<>();
        List<Mission> missions = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(resourceBundle.getString(field.getPropertyName()))) {
            ps.setString(1, state.toString());
            ps.setInt(2, size);
            ps.setInt(3, page * size);
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
    public int countByState(MissionState state) {
        try(PreparedStatement ps = connection.prepareStatement(resourceBundle.getString("query.mission.count.by.state"))) {
            ps.setString(1, state.toString());

            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()) {
                return resultSet.getInt(1);
            }

            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public boolean create(Mission mission) {
        try(PreparedStatement ps = connection.prepareStatement(resourceBundle.getString("query.mission.insert"))) {
            MissionMapper.basicFillStatement(ps, mission);

            ps.execute();

            return ps.getUpdateCount() > 0;
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

            ps.execute();

            return ps.getUpdateCount() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public boolean updateMissionState(Mission mission, MissionState state) {
        try (PreparedStatement ps = connection.prepareStatement(resourceBundle.getString("query.mission.update.state"))) {
            ps.setString(1, state.toString());
            ps.setInt(2, mission.getId());

            ps.execute();

            return ps.getUpdateCount() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public boolean pass(User user, Mission mission) {
        try (PreparedStatement ps = connection.prepareStatement(resourceBundle.getString("query.mission.user.pass"))) {
            ps.setInt(1, mission.getId());
            ps.setInt(2, user.getId());

            ps.execute();

            return ps.getUpdateCount() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public boolean delete(Mission mission) {
        try(PreparedStatement ps = connection.prepareStatement(resourceBundle.getString("query.mission.delete"))) {
            ps.setInt(1, mission.getId());

            ps.execute();

            return ps.getUpdateCount() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public boolean cancel(User user, Mission mission) {
        try(PreparedStatement ps = connection.prepareStatement(resourceBundle.getString("query.mission.user.cancel"))) {
            ps.setInt(1, mission.getId());
            ps.setInt(2, user.getId());

            ps.execute();

            return ps.getUpdateCount() > 0;
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
