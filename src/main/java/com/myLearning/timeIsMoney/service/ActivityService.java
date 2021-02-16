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


    public List<Activity> findAll() {
        try(ActivityDao activityDao = daoFactory.createActivityDao()) {
            return activityDao.findAll();
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

    public List<Activity> findActivePageable(int page, int size) {
        try(ActivityDao activityDao = daoFactory.createActivityDao()) {
            return activityDao.findActivePageableProxy(page, size);
        }
    }

    public List<Activity> findArchivedPageable(int page, int size) {
        try(ActivityDao activityDao = daoFactory.createActivityDao()) {
            return activityDao.findArchivedPageableProxy(page, size);
        }
    }

}
