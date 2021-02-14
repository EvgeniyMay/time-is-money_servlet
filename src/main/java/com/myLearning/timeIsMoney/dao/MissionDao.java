package com.mylearning.timeismoney.dao;

import com.mylearning.timeismoney.dto.UsersAndActivities;
import com.mylearning.timeismoney.entity.Mission;

public interface MissionDao extends GenericDao<Mission> {

    public UsersAndActivities getUsersAndActivities();
}
