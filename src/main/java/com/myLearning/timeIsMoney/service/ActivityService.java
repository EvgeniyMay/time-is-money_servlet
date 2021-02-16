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

    public boolean deleteById(int id) {
        try(ActivityDao activityDao = daoFactory.createActivityDao()) {
            return activityDao.deleteById(id);
        }
    }

    public int getCount() {
        try(ActivityDao activityDao = daoFactory.createActivityDao()) {
            return activityDao.getCount();
        }
    }

    public List<Activity> findActivePageable(int page, int size) {
        try(ActivityDao activityDao = daoFactory.createActivityDao()) {
            return activityDao.findActivePageableProxy(page, size);
        }
    }
}
