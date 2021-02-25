package com.mylearning.timeismoney.service;

import com.mylearning.timeismoney.dao.ActivityDao;
import com.mylearning.timeismoney.dao.DaoFactory;
import com.mylearning.timeismoney.entity.Activity;
import com.mylearning.timeismoney.exception.DaoException;
import com.mylearning.timeismoney.exception.ServiceException;
import com.mylearning.timeismoney.exception.ServiceLogicException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * class for all operation with activities
 */
public class ActivityService {
    /**
     * Logger for logging important information
     */
    private final Logger logger = LogManager.getLogger();

    /**
     * Dao for dao creating
     */
    private final DaoFactory daoFactory;

    public ActivityService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    /**
     * create new activity
     * @param activity to create
     * @return result of creating
     */
    public boolean create(Activity activity) throws ServiceLogicException {
        try(ActivityDao activityDao = daoFactory.createActivityDao()) {
            boolean created = activityDao.create(activity);

            if(!created) {
                throw new ServiceLogicException("Creation error");
            }

            return true;
        } catch (DaoException e) {
            logger.warn("Creation error");
            throw new ServiceException("Creation error", e);
        }
    }

    /**
     * get pageable list of activities
     * @param page page number
     * @param size max size of list
     * @param isActive state of activities to find
     * @return list of activities by all finding settings
     */
    public List<Activity> findPageableByState(int page, int size, boolean isActive) {
        try(ActivityDao activityDao = daoFactory.createActivityDao()) {
            return activityDao.findPageableByState(page, size, isActive);
        } catch (DaoException e) {
            logger.warn("Find pageable error");
            throw new ServiceException("Find pageable error", e);
        }
    }

    /**
     * find all active activities without all active missions
     * @return list of active without all active missions
     */
    public List<Activity> findActiveProxy() {
        try (ActivityDao activityDao = daoFactory.createActivityDao()) {
            return activityDao.findActiveProxy();
        } catch (DaoException e) {
            logger.warn("Find active activities error");
            throw new ServiceException("Find active activities error", e);
        }
    }

    /**
     * get activity by id
     * @param id of activity to find
     * @return activity with id from param
     */
    public Activity findById(int id) {
        try(ActivityDao activityDao = daoFactory.createActivityDao()) {
            return activityDao.findById(id)
                    .orElseThrow(() -> new DaoException("Activity not found"));
        } catch (DaoException e) {
            logger.warn("Find by id error");
            throw new ServiceException("Find by id error", e);
        }
    }

    /**
     * update activity
     * @param activity to update
     * @return result of updating
     */
    public boolean update(Activity activity) throws ServiceLogicException {
        try(ActivityDao activityDao = daoFactory.createActivityDao()) {
            boolean updated = activityDao.update(activity);

            if(!updated) {
                throw new ServiceLogicException("Update error");
            }

            return true;
        } catch (DaoException e) {
            logger.warn("Update error");
            throw new ServiceException("Update error", e);
        }
    }

    /**
     * change state of activity for archived
     * @param id of activity to archived
     * @return result of archiving
     */
    public boolean archiveById(int id) throws ServiceLogicException {
        try(ActivityDao activityDao = daoFactory.createActivityDao()) {
            boolean archived = activityDao.updateStateById(id, false);

            if(!archived) {
                throw new ServiceLogicException("Archive error");
            }

            return true;
        } catch (DaoException e) {
            logger.warn("Archive error");
            throw new ServiceException("Archive error", e);
        }
    }

    /**
     * change state of activity for active
     * @param id of activity to activate
     * @return result of activating
     */
    public boolean activateById(int id) throws ServiceLogicException {
        try(ActivityDao activityDao = daoFactory.createActivityDao()) {
            boolean activated = activityDao.updateStateById(id, true);

            if(!activated) {
                throw new ServiceLogicException("Arhive arror");
            }

            return true;
        } catch (DaoException e) {
            logger.warn("Active error");
            throw new ServiceException("Active error", e);
        }
    }

    /**
     * @return count activities with active state
     */
    public int getActiveCount() {
        try(ActivityDao activityDao = daoFactory.createActivityDao()) {
            return activityDao.getCountByState(true);
        } catch (DaoException e) {
            logger.warn("Active activities counting error");
            throw new ServiceException("Active activities counting error", e);
        }
    }

    /**
     * @return count activities with archived state
     */
    public int getArchivedCount() {
        try(ActivityDao activityDao = daoFactory.createActivityDao()) {
            return activityDao.getCountByState(false);
        } catch (DaoException e) {
            logger.warn("Archived activities counting error");
            throw new ServiceException("Archived activities counting error", e);
        }
    }
}
