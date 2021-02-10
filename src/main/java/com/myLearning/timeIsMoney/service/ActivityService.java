package com.myLearning.timeIsMoney.service;

import com.myLearning.timeIsMoney.dao.ActivityDao;
import com.myLearning.timeIsMoney.dao.DaoFactory;
import com.myLearning.timeIsMoney.dto.ActivityDto;
import com.myLearning.timeIsMoney.entity.Activity;

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
        return activityDao.findById(id);
    }

    public boolean save(ActivityDto activityDto) {
        return activityDao.save(activityDto);
    }

    public boolean update(Activity activity) {
        return activityDao.update(activity);
    }

    public boolean deleteById(int id) {
        return activityDao.deleteById(id);
    }

}
