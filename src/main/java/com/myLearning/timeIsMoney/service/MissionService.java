package com.mylearning.timeismoney.service;

import com.mylearning.timeismoney.dao.DaoFactory;
import com.mylearning.timeismoney.dao.MissionDao;
import com.mylearning.timeismoney.dto.UsersAndActivities;
import com.mylearning.timeismoney.entity.Mission;
import com.mylearning.timeismoney.entity.User;
import com.mylearning.timeismoney.entity.enums.MissionField;
import com.mylearning.timeismoney.entity.enums.MissionState;
import com.mylearning.timeismoney.entity.enums.Role;
import com.mylearning.timeismoney.exception.DaoException;
import com.mylearning.timeismoney.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class MissionService {

    private final Logger logger = LogManager.getLogger();

    public final DaoFactory daoFactory;

    public MissionService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
        logger.info("Mission service created");
    }


    public List<Mission> findPageable(int page, int size, MissionState state, MissionField field) {
        try(MissionDao missionDao = daoFactory.createMissionDao()) {
            return missionDao.findPageableSortedBy(page, size, state, field);
        } catch (DaoException e) {
            logger.warn("Find pageable error");
            throw new ServiceException("Find pageable error", e);
        }
    }

    public int countByState(MissionState state) {
        try(MissionDao missionDao = daoFactory.createMissionDao()) {
            return missionDao.countByState(state);
        } catch (DaoException e) {
            logger.warn("Count finding error");
            throw new ServiceException("Count finding error", e);
        }
    }

    public UsersAndActivities getUsersAndActivities() {
        try(MissionDao missionDao = daoFactory.createMissionDao()) {
            return missionDao.getUsersAndActivities();
        } catch (DaoException e) {
            logger.warn("Get users and activities error");
            throw new ServiceException("Get users and activities error", e);
        }
    }

    public boolean create(Mission mission) {
        try (MissionDao missionDao = daoFactory.createMissionDao()) {
            return missionDao.create(mission);
        } catch (DaoException e) {
            logger.warn("Create error");
            throw new ServiceException("Create error", e);
        }
    }

    public boolean updateState(Mission mission, MissionState state) {
        try(MissionDao missionDao = daoFactory.createMissionDao()) {
            return missionDao.updateState(mission, state);
        } catch (DaoException e) {
            logger.warn("Update error");
            throw new ServiceException("Update error", e);
        }
    }

    public boolean pass(User user, Mission mission) {
        try(MissionDao missionDao = daoFactory.createMissionDao()) {
            if(MissionState.ACTIVE.equals(mission.getState())) {
                missionDao.userUpdateState(user, mission, MissionState.PASSED);
            }
            return  false;
        } catch (DaoException e) {
            logger.warn("Pass error");
            throw new ServiceException("Pass error", e);
        }
    }

    public boolean cancel(User user, Mission mission) {
        try(MissionDao missionDao = daoFactory.createMissionDao()) {
            if(Role.ADMIN.equals(user.getRole())) {
                return missionDao.delete(mission);
            }
            return missionDao.cancel(user, mission);
        } catch (DaoException e) {
            logger.warn("GCancel error");
            throw new ServiceException("Cancel error", e);
        }
    }
}
