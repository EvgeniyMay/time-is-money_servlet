package com.myLearning.timeIsMoney.dao;

import com.myLearning.timeIsMoney.entity.Activity;

import java.util.List;
import java.util.Optional;

public interface ActivityDao extends GenericDao<Activity> {

    @Override
    boolean create(Activity entity);

    @Override
    Optional<Activity> findById(int id);

    @Override
    List<Activity> findAll();

    @Override
    boolean update(Activity entity);

    boolean deleteById(int id);

    @Override
    void close();
}
