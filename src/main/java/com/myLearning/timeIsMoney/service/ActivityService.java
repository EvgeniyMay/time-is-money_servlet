package com.mylearning.timeismoney.service;

import com.mylearning.timeismoney.dao.ActivityDao;
import com.mylearning.timeismoney.dao.DaoFactory;
import com.mylearning.timeismoney.entity.Activity;
import com.mylearning.timeismoney.exception.DaoException;
import com.mylearning.timeismoney.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ActivityService {

    private final Logger logger = LogManager.getLogger();

    private final DaoFactory daoFactory;

    public ActivityService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }


    public List<Activity> findPageableByState(int page, int size, boolean isActive) {
        try(ActivityDao activityDao = daoFactory.createActivityDao()) {
            return activityDao.findPageableByState(page, size, isActive);
        } catch (DaoException e) {
            logger.warn("Find pageable error");
            throw new ServiceException("Find pageable error", e);
        }
    }

    public List<Activity> findActiveProxy() {
        try (ActivityDao activityDao = daoFactory.createActivityDao()) {
            return activityDao.findActiveProxy();
        } catch (DaoException e) {
            logger.warn("Find active activities error");
            throw new ServiceException("Find active activities error", e);
        }
    }

    public Activity findById(int id) {
        try(ActivityDao activityDao = daoFactory.createActivityDao()) {
            return activityDao.findById(id)
                    .orElseThrow(() -> new DaoException("Activity not found"));
        } catch (DaoException e) {
            logger.warn("Find by id error");
            throw new ServiceException("Find by id error", e);
        }
    }

    public boolean create(Activity activity) {
        try(ActivityDao activityDao = daoFactory.createActivityDao()) {
            return activityDao.create(activity);
        } catch (DaoException e) {
            logger.warn("Creation error");
            throw new ServiceException("Creation error", e);
        }
    }

    public boolean update(Activity activity) {
        try(ActivityDao activityDao = daoFactory.createActivityDao()) {
            return activityDao.update(activity);
        } catch (DaoException e) {
            logger.warn("Update error");
            throw new ServiceException("Update error", e);
        }
    }

    public boolean archiveById(int id) {
        try(ActivityDao activityDao = daoFactory.createActivityDao()) {
            return activityDao.updateStateById(id, false);
        } catch (DaoException e) {
            logger.warn("Archive error");
            throw new ServiceException("Archive error", e);
        }
    }

    public boolean activateById(int id) {
        try(ActivityDao activityDao = daoFactory.createActivityDao()) {
            return activityDao.updateStateById(id, true);
        } catch (DaoException e) {
            logger.warn("Active error");
            throw new ServiceException("Active error", e);
        }
    }

    public int getActiveCount() {
        try(ActivityDao activityDao = daoFactory.createActivityDao()) {
            return activityDao.getCountByState(true);
        } catch (DaoException e) {
            logger.warn("Active activities counting error");
            throw new ServiceException("Active activities counting error", e);
        }
    }

    public int getArchivedCount() {
        try(ActivityDao activityDao = daoFactory.createActivityDao()) {
            return activityDao.getCountByState(false);
        } catch (DaoException e) {
            logger.warn("Archived activities counting error");
            throw new ServiceException("Archived activities counting error", e);
        }
    }
}
