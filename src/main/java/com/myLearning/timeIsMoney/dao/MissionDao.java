package com.mylearning.timeismoney.dao;

import com.mylearning.timeismoney.dto.UsersAndActivities;
import com.mylearning.timeismoney.entity.Mission;
import com.mylearning.timeismoney.entity.enums.MissionState;

public interface MissionDao extends GenericDao<Mission> {

    public UsersAndActivities getUsersAndActivities();

    public boolean updateMissionState(Mission mission, MissionState state);
}
