package com.myLearning.timeIsMoney.dao;

import com.myLearning.timeIsMoney.dto.ActivityDto;
import com.myLearning.timeIsMoney.entity.Activity;

import java.util.List;

public interface ActivityDao {

    List<Activity> findAll();

    Activity findById(int id);

    boolean save(ActivityDto activityDto);

    boolean deleteById(int id);

    boolean update(Activity activity);

}
