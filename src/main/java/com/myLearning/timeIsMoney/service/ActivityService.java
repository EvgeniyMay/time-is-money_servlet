package com.mylearning.timeismoney.service;

import com.mylearning.timeismoney.dao.ActivityDao;
import com.mylearning.timeismoney.dao.DaoFactory;
import com.mylearning.timeismoney.entity.Activity;

import java.util.List;

public class ActivityService {

    private final ActivityDao activityDao;

    public ActivityService(DaoFactory daoFactory) {
        this.activityDao = daoFactory.createActivityDao();
    }


    public List<Activity> findAll() {
        return activityDao.findAll();
    }

    public Activity findById(int id) {
        return activityDao.findById(id).orElseThrow(() -> new RuntimeException());
    }

    public boolean create(Activity activity) {
        return activityDao.create(activity);
    }

    public boolean update(Activity activity) {
        return activityDao.update(activity);
    }

    public boolean deleteById(int id) {
        return activityDao.deleteById(id);
    }

}
