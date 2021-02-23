package com.mylearning.timeismoney.dao.impl;

import com.mylearning.timeismoney.dao.ActivityDao;
import com.mylearning.timeismoney.dao.mapper.ActivityMapper;
import com.mylearning.timeismoney.dao.mapper.MissionMapper;
import com.mylearning.timeismoney.dao.mapper.UserMapper;
import com.mylearning.timeismoney.entity.Activity;
import com.mylearning.timeismoney.entity.Mission;
import com.mylearning.timeismoney.entity.User;
import com.mylearning.timeismoney.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.*;

public class JdbcActivityDao implements ActivityDao {

    private final Logger logger = LogManager.getLogger();

    private final Connection connection;
    private final static ResourceBundle rb = ResourceBundle.getBundle("database");

    public JdbcActivityDao(Connection connection) {
        this.connection = connection;
    }


    @Override
    public List<Activity> findAll() {
        Map<Integer, Activity> activityMap = new HashMap<>();
        Map<Integer, User> userMap = new HashMap<>();

        try (PreparedStatement ps = connection.prepareStatement(rb.getString("query.activity.find.all"))) {
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
            logger.warn("Can not find activities");
            throw new DaoException("Can not find activities");
        }
    }

    @Override
    public List<Activity> findActiveProxy() {
        List<Activity> activities = new ArrayList<>();
        try(PreparedStatement ps = connection.prepareStatement(rb.getString("query.activity.find.active.proxy"));
            ResultSet resultSet = ps.executeQuery()) {
            while (resultSet.next()) {
                Activity activity = ActivityMapper.getFromResultSet(resultSet);
                activities.add(activity);
            }

            return activities;
        } catch (SQLException e) {
            logger.warn("Can not find activities");
            throw new DaoException("Can not find activities");
        }
    }

    @Override
    public Optional<Activity> findById(int id) {
        Map<Integer, User> userMap = new HashMap<>();
        Activity activity = null;

        try (PreparedStatement ps = connection.prepareStatement(rb.getString("query.activity.find.by.id"))) {
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
            logger.warn("Can not find activity");
            throw new DaoException("Can not find activity");
        }
    }

    @Override
    public List<Activity> findPageableByState(int page, int size, boolean isActive) {
        List<Activity> activities = new ArrayList<>();

        try(PreparedStatement ps = connection.prepareStatement(rb.getString("query.activity.find.by.state.pageable.proxy"))) {
            ps.setBoolean(1, !isActive);
            ps.setInt(2, size);
            ps.setInt(3, page * size);

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Activity activity = ActivityMapper.getPageableProxy(resultSet);
                activities.add(activity);
            }

            return activities;
        } catch (SQLException e) {
            logger.warn("Can not find activities");
            throw new DaoException("Can not find activities");
        }
    }

    @Override
    public boolean create(Activity activity) {
        try (PreparedStatement ps = connection.prepareStatement(rb.getString("query.activity.insert"))) {
            ActivityMapper.basicFillStatement(ps, activity);

            ps.execute();

            return ps.getUpdateCount() > 0;
        } catch (SQLException e) {
            logger.warn("Can not create activity");
            throw new DaoException("Can not create activity");
        }
    }

    @Override
    public boolean updateStateById(int id, boolean makeActive) {
        try (PreparedStatement ps = connection.prepareStatement(rb.getString("query.activity.update.state.by.id"))) {
            ps.setBoolean(1, !makeActive);
            ps.setInt(2, id);

            ps.execute();

            return ps.getUpdateCount() > 0;
        } catch (SQLException e) {
            logger.warn("Can not update activity");
            throw new DaoException("Can not update activity");
        }
    }

    @Override
    public boolean update(Activity activity) {
        try (PreparedStatement ps = connection.prepareStatement(rb.getString("query.activity.update"))) {
            ActivityMapper.basicFillStatement(ps, activity);
            ps.setInt(3, activity.getId());

            ps.execute();

            return ps.getUpdateCount() > 0;
        } catch (SQLException e) {
            logger.warn("Can not update activity");
            throw new DaoException("Can not update activity");
        }
    }

    @Override
    public int getCountByState(boolean isActive) {
        try(PreparedStatement ps = connection.prepareStatement(rb.getString("query.activity.count.by.state"))) {
            ps.setBoolean(1, !isActive);
            ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()) {
                return resultSet.getInt(1);
            }

            return 0;
        } catch (SQLException e) {
            logger.warn("Can not find count");
            throw new DaoException("Can not find count");
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            logger.warn("Can not close connection");
        }
    }
}