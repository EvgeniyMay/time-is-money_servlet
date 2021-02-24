package com.mylearning.timeismoney.dao;

/**
 * Ensures than class can return different dao
 */
public interface DaoFactory {

    ActivityDao createActivityDao();

    UserDao createUserDao();

    MissionDao createMissionDao();

}
