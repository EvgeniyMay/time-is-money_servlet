package com.mylearning.timeismoney.dao;

public interface DaoFactory {

    ActivityDao createActivityDao();

    UserDao createUserDao();

    MissionDao createMissionDao();

}
