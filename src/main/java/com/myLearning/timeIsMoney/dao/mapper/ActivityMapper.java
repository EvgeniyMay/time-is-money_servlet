package com.myLearning.timeIsMoney.dao.mapper;

import com.myLearning.timeIsMoney.entity.Activity;

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
}
