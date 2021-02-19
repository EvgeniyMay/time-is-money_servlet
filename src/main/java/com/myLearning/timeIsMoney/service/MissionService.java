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

    public List<Mission> findByState(MissionState state) {
        try (MissionDao missionDao = daoFactory.createMissionDao()) {
            return missionDao.findByState(state);
        }
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

    public boolean createMission(Mission mission) {
        try (MissionDao missionDao = daoFactory.createMissionDao()) {
            return missionDao.create(mission);
        }
    }

    public boolean updateMissionState(Mission mission, MissionState state) {
        try(MissionDao missionDao = daoFactory.createMissionDao()) {
            return missionDao.updateMissionState(mission, state);
        }
    }

    public boolean passMission(User user, Mission mission) {
        try(MissionDao missionDao = daoFactory.createMissionDao()) {
            return missionDao.pass(user, mission);
        }
    }

    public boolean delete(Mission mission) {
        try(MissionDao missionDao = daoFactory.createMissionDao()) {
            return missionDao.delete(mission);
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
