package com.mylearning.timeismoney.dao;

import com.mylearning.timeismoney.entity.Activity;

import java.util.List;
import java.util.Optional;

public interface ActivityDao extends GenericDao<Activity> {

    List<Activity> findAllProxy();

    List<Activity> findActiveProxy();

    List<Activity> findActivePageableProxy(int page, int size);

    List<Activity> findArchivedPageableProxy(int page, int size);

    Optional<Activity> findById(int id);

    boolean archiveById(int id);

    boolean activateById(int id);

    int getActiveCount();

    int getArchivedCount();

}
