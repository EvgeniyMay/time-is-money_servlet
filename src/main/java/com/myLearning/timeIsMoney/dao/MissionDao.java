package com.mylearning.timeismoney.dao;

import com.mylearning.timeismoney.dto.UsersAndActivities;
import com.mylearning.timeismoney.entity.Mission;
import com.mylearning.timeismoney.entity.User;
import com.mylearning.timeismoney.entity.enums.MissionField;
import com.mylearning.timeismoney.entity.enums.MissionState;

import java.util.List;

/**
 * Ensures realization of mission access object
 */
public interface MissionDao extends GenericDao<Mission> {

    /**
     * get pageable list of missions
     * @param page page number
     * @param size max size of list
     * @param state state of missions to find
     * @param field field for sorting
     * @return list of missions by all params settings
     */
    List<Mission> findPageableSortedBy(int page, int size, MissionState state, MissionField field);

    /**
     * count of entities by state to get quantity of pages for pagination
     * @param state state of missions count to find
     * @return count of missions by state
     */
    int countByState(MissionState state);

    /**
     * @return all able users and missions
     */
    UsersAndActivities getUsersAndActivities();

    /**
     * update state of mission without checking
     * @param mission mission to change
     * @param state new mission state
     * @return result of mission updating
     */
    boolean updateState(Mission mission, MissionState state);

    /**
     * delete mission without checking
     * @param mission mission to delete
     * @return result of deleting
     */
    boolean delete(Mission mission);

    /**
     * update state of mission with check of user owns mission
     * @param user owner of mission to update
     * @param mission mission to update
     * @param state new mission state
     * @return result of updating
     */
    boolean userUpdateState (User user, Mission mission, MissionState state);

    /**
     * delete mission with check of user owns mission
     * @param user owner of mission to cancel
     * @param mission mission to cancel
     * @return result of updating
     */
    boolean cancel(User user, Mission mission);
}
