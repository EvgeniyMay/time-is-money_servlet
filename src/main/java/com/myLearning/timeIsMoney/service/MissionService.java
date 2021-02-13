package com.mylearning.timeismoney.service;

import com.mylearning.timeismoney.dao.DaoFactory;
import com.mylearning.timeismoney.dao.MissionDao;
import com.mylearning.timeismoney.entity.Mission;

import java.util.List;

public class MissionService {

    public final MissionDao missionDao;

    public MissionService(DaoFactory daoFactory) {
        this.missionDao = daoFactory.createMissionDao();
    }


    public List<Mission> findAll() {
        return missionDao.findAll();
    }
}
