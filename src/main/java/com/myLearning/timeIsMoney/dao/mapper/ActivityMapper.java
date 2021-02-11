package com.myLearning.timeIsMoney.dao.mapper;

import com.myLearning.timeIsMoney.entity.Activity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ActivityMapper implements EntityMapper<Activity> {

    @Override
    public Activity getFromResultSet(ResultSet resultSet) throws SQLException {
        return null;
    }
}
