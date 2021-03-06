package com.mylearning.timeismoney.entity;

import java.util.List;

public class Activity {

    private int id;
    private String name;
    private String description;

    private List<Mission> missions;

    private boolean isArchived;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public List<Mission> getMissions() {
        return missions;
    }
    public void setMissions(List<Mission> missions) {
        this.missions = missions;
    }

    public boolean isArchived() {
        return isArchived;
    }
    public void setArchived(boolean archived) {
        isArchived = archived;
    }



    public static class Builder {

        private final Activity newActivity;

        public Builder() {
            newActivity = new Activity();
        }

        public Builder id(int id) {
            newActivity.id = id;
            return this;
        }

        public Builder name(String name) {
            newActivity.name = name;
            return this;
        }

        public Builder description(String description) {
            newActivity.description = description;
            return this;
        }

        public Builder missions(List<Mission> missions) {
            newActivity.missions = missions;
            return this;
        }

        public Builder isArchived(boolean isArchived) {
            newActivity.isArchived = isArchived;
            return this;
        }

        public Activity build() {
            return newActivity;
        }
    }
}
