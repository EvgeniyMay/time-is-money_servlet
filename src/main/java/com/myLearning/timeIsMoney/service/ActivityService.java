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

    public List<Activity> findActivePageable(int page, int size) {
        try(ActivityDao activityDao = daoFactory.createActivityDao()) {
            return activityDao.findActivePageable(page, size);
        }
    }

    public List<Activity> findArchivedPageable(int page, int size) {
        try(ActivityDao activityDao = daoFactory.createActivityDao()) {
            return activityDao.findArchivedPageable(page, size);
        }
    }

    public List<Activity> findActive() {
        try(ActivityDao activityDao = daoFactory.createActivityDao()) {
            return activityDao.findActive();
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
            return activityDao.archiveById(id);
        }
    }

    public boolean activateById(int id) {
        try(ActivityDao activityDao = daoFactory.createActivityDao()) {
            return activityDao.activateById(id);
        }
    }

    public int getActiveCount() {
        try(ActivityDao activityDao = daoFactory.createActivityDao()) {
            return activityDao.getActiveCount();
        }
    }

    public int getArchivedCount() {
        try(ActivityDao activityDao = daoFactory.createActivityDao()) {
            return activityDao.getArchivedCount();
        }
    }



}
