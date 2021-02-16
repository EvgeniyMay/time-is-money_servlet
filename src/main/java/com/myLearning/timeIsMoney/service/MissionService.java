package com.mylearning.timeismoney.service;

import com.mylearning.timeismoney.dao.DaoFactory;
import com.mylearning.timeismoney.dao.MissionDao;
import com.mylearning.timeismoney.dto.UsersAndActivities;
import com.mylearning.timeismoney.entity.Mission;
import com.mylearning.timeismoney.entity.User;
import com.mylearning.timeismoney.entity.enums.MissionState;

import java.util.List;

public class MissionService {

    public final DaoFactory daoFactory;

    public MissionService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public List<Mission> findAll() {
        try(MissionDao missionDao = daoFactory.createMissionDao()) {
            return missionDao.findAll();
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

    public boolean userUpdateMissionState(User user, Mission mission, MissionState state) {
        try(MissionDao missionDao = daoFactory.createMissionDao()) {
            return missionDao.userUpdateMissionState(user, mission, state);
        }
    }

    public boolean delete(Mission mission) {
        try(MissionDao missionDao = daoFactory.createMissionDao()) {
            return missionDao.delete(mission);
        }
    }

}
