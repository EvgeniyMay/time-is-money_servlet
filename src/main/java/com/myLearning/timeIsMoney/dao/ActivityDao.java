package com.mylearning.timeismoney.dao;

import com.mylearning.timeismoney.entity.Activity;

import java.util.List;
import java.util.Optional;

public interface ActivityDao extends GenericDao<Activity> {

    List<Activity> findActiveProxy();

    List<Activity> findPageableByState(int page, int size, boolean isActive);

    Optional<Activity> findById(int id);

    boolean updateStateById(int id, boolean makeActive);

    int getCountByState(boolean isActive);
}
