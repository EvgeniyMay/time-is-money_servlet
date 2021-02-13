package com.mylearning.timeismoney.entity;

import com.mylearning.timeismoney.entity.enums.MissionState;

import java.time.LocalDateTime;

public class Mission {

    private int id;

    private User user;

    private Activity activity;

    private MissionState state;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public Activity getActivity() {
        return activity;
    }
    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public MissionState getState() {
        return state;
    }
    public void setState(MissionState state) {
        this.state = state;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
