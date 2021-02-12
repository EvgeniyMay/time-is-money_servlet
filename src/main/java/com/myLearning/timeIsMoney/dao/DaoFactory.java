package com.myLearning.timeIsMoney.dao;

public interface DaoFactory {

    ActivityDao createActivityDao();

    UserDao createUserDao();

    MissionDao createMissionDao();

}
