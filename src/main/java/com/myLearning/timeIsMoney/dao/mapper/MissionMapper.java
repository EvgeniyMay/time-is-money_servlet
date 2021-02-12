package com.myLearning.timeIsMoney.dao.mapper;

import com.myLearning.timeIsMoney.entity.Mission;
import com.myLearning.timeIsMoney.entity.enums.MissionState;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MissionMapper {

    public static Mission getFromResultSet(ResultSet resultSet) throws SQLException {
        Mission mission = new Mission();
        mission.setId(resultSet.getInt("mission_id"));
        mission.setState(MissionState.valueOf(resultSet.getString("mission_state")));
        mission.setStartTime(resultSet.getTimestamp("mission_start_time").toLocalDateTime());
        mission.setEndTime(resultSet.getTimestamp("mission_end_time").toLocalDateTime());

        return mission;
    }
}