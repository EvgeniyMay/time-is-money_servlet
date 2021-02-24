package com.mylearning.timeismoney.dao.impl;

import com.mylearning.timeismoney.dao.UserDao;
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

/**
 * Jdbc realization of UserDao
 * @see com.mylearning.timeismoney.dao.UserDao
 */
public class JdbcUserDao implements UserDao {

    private final static Logger logger = LogManager.getLogger();

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
            logger.warn("Can not find users");
            throw new DaoException("Can not find users");
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
            logger.warn("Can not find user");
            throw new DaoException("Can not find user");
        }
    }


    @Override
    public Optional<User> findByLoginProxy(String login) {
        try(PreparedStatement ps = connection.prepareStatement(resourceBundle.getString("query.user.find.by.login.proxy"))) {
            ps.setString(1, login);
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();

            User user = UserMapper.getUserDetailsFromResultSet(resultSet);
            return Optional.of(user);
        } catch (SQLException e) {
            logger.warn("Can not find user");
            throw new DaoException("Can not find user");
        }
    }

    @Override
    public boolean create(User user) {
        try(PreparedStatement ps = connection.prepareStatement(resourceBundle.getString("query.user.insert"))) {
            UserMapper.basicFillStatement(ps, user);

            return ps.execute();
        } catch (SQLException e) {
            logger.warn("Can not create user");
            throw new DaoException("Can not create user");
        }
    }

    @Override
    public boolean update(User user) {
        try(PreparedStatement ps = connection.prepareStatement(resourceBundle.getString("query.user.update"))) {
            UserMapper.basicFillStatement(ps, user);
            ps.setInt(4, user.getId());

            return ps.execute();
        } catch (SQLException e) {
            logger.warn("Can not update user");
            throw new DaoException("Can not create user");
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
