package com.mylearning.timeismoney.dto;

import com.mylearning.timeismoney.entity.Activity;
import com.mylearning.timeismoney.entity.User;

import java.util.List;

public class UsersAndActivities {

    private List<User> users;
    private List<Activity> activities;


    public List<User> getUsers() {
        return users;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Activity> getActivities() {
        return activities;
    }
    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }
}
