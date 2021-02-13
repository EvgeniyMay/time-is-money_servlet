package com.mylearning.timeismoney.service;

import com.mylearning.timeismoney.dao.DaoFactory;
import com.mylearning.timeismoney.dao.MissionDao;
import com.mylearning.timeismoney.entity.Mission;

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
}
