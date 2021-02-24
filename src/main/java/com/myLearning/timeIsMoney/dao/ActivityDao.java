package com.mylearning.timeismoney.dao;

import com.mylearning.timeismoney.entity.Activity;

import java.util.List;
import java.util.Optional;

/**
 * Ensures realization of activity access object
 */
public interface ActivityDao extends GenericDao<Activity> {

    /**
     * find all active activities without all active missions
     * @return list of active without all active missions
     */
    List<Activity> findActiveProxy();

    /**
     * get pageable list of activities
     * @param page page number
     * @param size max size of list
     * @param isActive state of activities to find
     * @return list of activities by all finding settings
     */
    List<Activity> findPageableByState(int page, int size, boolean isActive);

    /**
     * get activity by id
     * @param id of activity to find
     * @return activity with id from param
     */
    Optional<Activity> findById(int id);

    /**
     * update mission state by id
     * @param id of mission to update
     * @param makeActive 'true' if activity should be active and 'false' if it should be archived
     * @return result of updating
     */
    boolean updateStateById(int id, boolean makeActive);

    /**
     * count of entities by state to get quantity of pages for pagination
     * @param isActive state of activity count to find
     *                 'true' if activity should be active and 'false' if it should be archived
     * @return count of activities by state
     */
    int getCountByState(boolean isActive);
}
