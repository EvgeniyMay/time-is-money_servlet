package com.mylearning.timeismoney.dao.mapper;

import com.mylearning.timeismoney.entity.Mission;
import com.mylearning.timeismoney.entity.enums.MissionState;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class MissionMapper {

    public static Mission getFromResultSet(ResultSet resultSet) throws SQLException {
        Mission mission = new Mission();
        mission.setId(resultSet.getInt("mission_id"));
        mission.setState(MissionState.valueOf(resultSet.getString("mission_state")));
        mission.setStartTime(resultSet.getTimestamp("mission_start_time").toLocalDateTime());
        mission.setEndTime(resultSet.getTimestamp("mission_end_time").toLocalDateTime());

        return mission;
    }

    public static void basicFillStatement(PreparedStatement ps, Mission mission) throws SQLException {
        ps.setInt(1,mission.getUser().getId());
        ps.setInt(2, mission.getActivity().getId());
        ps.setTimestamp(3, Timestamp.valueOf(mission.getStartTime()));
        ps.setTimestamp(4, Timestamp.valueOf(mission.getEndTime()));
        ps.setString(5, String.valueOf(mission.getState()));
    }
}
