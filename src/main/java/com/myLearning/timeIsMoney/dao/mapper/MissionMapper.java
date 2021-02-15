package com.mylearning.timeismoney.dao.mapper;

import com.mylearning.timeismoney.entity.Mission;
import com.mylearning.timeismoney.entity.enums.MissionState;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class MissionMapper {

    public static Mission getFromResultSet(ResultSet resultSet) throws SQLException {
        return new Mission.Builder()
                .id(resultSet.getInt("mission_id"))
                .state(MissionState.valueOf(resultSet.getString("mission_state")))
                .startTime(resultSet.getTimestamp("mission_start_time").toLocalDateTime())
                .endTime(resultSet.getTimestamp("mission_end_time").toLocalDateTime())
                .build();
    }

    public static void basicFillStatement(PreparedStatement ps, Mission mission) throws SQLException {
        ps.setInt(1, mission.getUser().getId());
        ps.setInt(2, mission.getActivity().getId());
        ps.setTimestamp(3, Timestamp.valueOf(mission.getStartTime()));
        ps.setTimestamp(4, Timestamp.valueOf(mission.getEndTime()));
        ps.setString(5, String.valueOf(mission.getState()));
    }
}
