package com.mylearning.timeismoney.dao;

import com.mylearning.timeismoney.dto.UsersAndActivities;
import com.mylearning.timeismoney.entity.Mission;
import com.mylearning.timeismoney.entity.enums.MissionState;

public interface MissionDao extends GenericDao<Mission> {

    UsersAndActivities getUsersAndActivities();

    boolean updateMissionState(Mission mission, MissionState state);

    boolean delete(Mission mission);
}
