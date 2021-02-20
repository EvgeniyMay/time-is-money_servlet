package com.mylearning.timeismoney.dao;

import com.mylearning.timeismoney.dto.UsersAndActivities;
import com.mylearning.timeismoney.entity.Mission;
import com.mylearning.timeismoney.entity.User;
import com.mylearning.timeismoney.entity.enums.MissionField;
import com.mylearning.timeismoney.entity.enums.MissionState;

import java.util.List;

public interface MissionDao extends GenericDao<Mission> {

    List<Mission> findPageableSortedBy(int page, int size, MissionState state, MissionField field);

    int countByState(MissionState state);

    UsersAndActivities getUsersAndActivities();

    boolean updateMissionState(Mission mission, MissionState state);

    boolean delete(Mission mission);

    boolean pass(User user, Mission mission);

    boolean cancel(User user, Mission mission);
}
