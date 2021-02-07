package com.myLearning.timeIsMoney.dao;

import com.myLearning.timeIsMoney.entity.Activity;

import java.util.List;

public interface ActivityDao {

    List<Activity> findAll();
}
