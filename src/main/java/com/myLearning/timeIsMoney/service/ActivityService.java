package com.mylearning.timeismoney.service;

import com.mylearning.timeismoney.dao.ActivityDao;
import com.mylearning.timeismoney.dao.DaoFactory;
import com.mylearning.timeismoney.entity.Activity;

import java.util.List;

public class ActivityService {

    private final DaoFactory daoFactory;

    public ActivityService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }


    public List<Activity> findPageableByState(int page, int size, boolean isActive) {
        try(ActivityDao activityDao = daoFactory.createActivityDao()) {
            return activityDao.findPageableByState(page, size, isActive);
        }
    }

    public List<Activity> findActiveProxy() {
        try (ActivityDao activityDao = daoFactory.createActivityDao()) {
            return activityDao.findActiveProxy();
        }
    }

    public Activity findById(int id) {
        try(ActivityDao activityDao = daoFactory.createActivityDao()) {
            return activityDao.findById(id)
                    .orElseThrow(() -> new RuntimeException());
        }
    }

    public boolean create(Activity activity) {
        try(ActivityDao activityDao = daoFactory.createActivityDao()) {
            return activityDao.create(activity);
        }
    }

    public boolean update(Activity activity) {
        try(ActivityDao activityDao = daoFactory.createActivityDao()) {
            return activityDao.update(activity);
        }
    }

    public boolean archiveById(int id) {
        try(ActivityDao activityDao = daoFactory.createActivityDao()) {
            return activityDao.updateStateById(id, false);
        }
    }

    public boolean activateById(int id) {
        try(ActivityDao activityDao = daoFactory.createActivityDao()) {
            return activityDao.updateStateById(id, true);
        }
    }

    public int getActiveCount() {
        try(ActivityDao activityDao = daoFactory.createActivityDao()) {
            return activityDao.getCountByState(true);
        }
    }

    public int getArchivedCount() {
        try(ActivityDao activityDao = daoFactory.createActivityDao()) {
            return activityDao.getCountByState(false);
        }
    }



}
