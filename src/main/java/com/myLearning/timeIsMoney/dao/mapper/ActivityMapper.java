package com.mylearning.timeismoney.dao.mapper;

import com.mylearning.timeismoney.entity.Activity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ActivityMapper {

    public static Activity getFromResultSet(ResultSet resultSet) throws SQLException {
        Activity activity = new Activity();
        activity.setId(resultSet.getInt("activity_id"));
        activity.setName(resultSet.getString("activity_name"));
        activity.setDescription(resultSet.getString("activity_description"));

        activity.setMissions(new ArrayList<>());

        return activity;
    }

    public static Activity getProxyFromResultSet(ResultSet resultSet) throws SQLException {
        Activity activity = new Activity();

        activity.setId(resultSet.getInt("activity_id"));
        activity.setName(resultSet.getString("activity_name"));
        activity.setDescription(resultSet.getString("activity_description"));

        return activity;
    }

    public static void basicFillStatement(PreparedStatement ps, Activity activity) throws SQLException {
        ps.setString(1, activity.getName());
        ps.setString(2, activity.getDescription());
    }
}
