package com.mylearning.timeismoney.entity;

import com.mylearning.timeismoney.entity.enums.MissionState;

import java.time.LocalDateTime;
import java.util.List;

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

    public static class Builder {

        private final Mission newMission;

        public Builder() {
            newMission = new Mission();
        }

        public Builder id(int id) {
            newMission.id = id;
            return this;
        }

        public Builder user(User user){
            newMission.user = user;
            return this;
        }

        public Builder activity(Activity activity){
            newMission.activity = activity;
            return this;
        }

        public Builder state(MissionState state){
            newMission.state = state;
            return this;
        }

        public Builder startTime(LocalDateTime startTime){
            newMission.startTime = startTime;
            return this;
        }

        public Builder endTime(LocalDateTime endTime){
            newMission.endTime = endTime;
            return this;
        }

        public Mission build() {
            return newMission;
        }
    }
}
