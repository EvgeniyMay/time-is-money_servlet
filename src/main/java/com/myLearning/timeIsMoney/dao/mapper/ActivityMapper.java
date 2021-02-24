package com.mylearning.timeismoney.dao.mapper;

import com.mylearning.timeismoney.entity.Activity;
import com.mylearning.timeismoney.entity.Mission;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * work with Activity and ResultSet
 */
public class ActivityMapper {

    public static Activity getFromResultSet(ResultSet resultSet) throws SQLException {
        return new Activity.Builder()
                .id(resultSet.getInt("activity_id"))
                .name(resultSet.getString("activity_name"))
                .description(resultSet.getString("activity_description"))
                .isArchived(resultSet.getBoolean("activity_is_archived"))
                .missions(new ArrayList<>())
                .build();
    }

    public static Activity getPageableProxy(ResultSet resultSet) throws SQLException {
        //ToDo think about it
        List<Mission> missions = new ArrayList<>();
        for(int i = 0; i< resultSet.getInt("active_missions"); i++) {
            missions.add(new Mission());
        }

        return new Activity.Builder()
                .id(resultSet.getInt("activity_id"))
                .name(resultSet.getString("activity_name"))
                .description(resultSet.getString("activity_description"))
                .isArchived(resultSet.getBoolean("activity_is_archived"))
                .missions(missions)
                .build();
    }

    public static void basicFillStatement(PreparedStatement ps, Activity activity) throws SQLException {
        ps.setString(1, activity.getName());
        ps.setString(2, activity.getDescription());
    }
}
