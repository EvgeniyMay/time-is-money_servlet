package com.mylearning.timeismoney.service;

import com.mylearning.timeismoney.dao.DaoFactory;
import com.mylearning.timeismoney.dao.MissionDao;
import com.mylearning.timeismoney.dto.UsersAndActivities;
import com.mylearning.timeismoney.entity.Mission;
import com.mylearning.timeismoney.entity.User;
import com.mylearning.timeismoney.entity.enums.MissionField;
import com.mylearning.timeismoney.entity.enums.MissionState;
import com.mylearning.timeismoney.entity.enums.Role;

import java.util.List;

public class MissionService {

    public final DaoFactory daoFactory;

    public MissionService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }


    public List<Mission> findPageable(int page, int size, MissionState state, MissionField field) {
        try(MissionDao missionDao = daoFactory.createMissionDao()) {
            return missionDao.findPageableSortedBy(page, size, state, field);
        }
    }

    public int countByState(MissionState state) {
        try(MissionDao missionDao = daoFactory.createMissionDao()) {
            return missionDao.countByState(state);
        }
    }

    public UsersAndActivities getUsersAndActivities() {
        try(MissionDao missionDao = daoFactory.createMissionDao()) {
            return missionDao.getUsersAndActivities();
        }
    }

    public boolean create(Mission mission) {
        try (MissionDao missionDao = daoFactory.createMissionDao()) {
            return missionDao.create(mission);
        }
    }

    public boolean updateState(Mission mission, MissionState state) {
        try(MissionDao missionDao = daoFactory.createMissionDao()) {
            return missionDao.updateState(mission, state);
        }
    }

    public boolean pass(User user, Mission mission) {
        try(MissionDao missionDao = daoFactory.createMissionDao()) {
            if(MissionState.ACTIVE.equals(mission.getState())) {
                missionDao.userUpdateState(user, mission, MissionState.PASSED);
            }
            return  false;
        }
    }

    public boolean cancel(User user, Mission mission) {
        try(MissionDao missionDao = daoFactory.createMissionDao()) {
            if(Role.ADMIN.equals(user.getRole())) {
                return missionDao.delete(mission);
            }
            return missionDao.cancel(user, mission);
        }
    }
}
