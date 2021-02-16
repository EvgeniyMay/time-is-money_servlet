package com.mylearning.timeismoney.entity.enums;

public enum MissionField {
    USER_ID("query.mission.find.by.state.pageable.sorted.by.user.id"),
    ACTIVITY_ID("query.mission.find.by.state.pageable.sorted.by.activity.id"),
    START_TIME("query.mission.find.by.state.pageable.sorted.by.start.time"),
    END_TIME("query.mission.find.by.state.pageable.sorted.by.end.time");


    private String propertyName;

    MissionField(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyName() {
        return propertyName;
    }
}
