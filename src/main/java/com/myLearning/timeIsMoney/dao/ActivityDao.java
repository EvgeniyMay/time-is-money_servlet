package com.mylearning.timeismoney.dao;

import com.mylearning.timeismoney.entity.Activity;

import java.util.List;
import java.util.Optional;

public interface ActivityDao extends GenericDao<Activity> {

    List<Activity> findAllProxy();

    Optional<Activity> findById(int id);

    boolean deleteById(int id);

    int getCount();

    List<Activity> findActivePageableProxy(int page, int size);
}
