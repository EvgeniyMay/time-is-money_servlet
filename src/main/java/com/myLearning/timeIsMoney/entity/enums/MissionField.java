package com.mylearning.timeismoney.entity.enums;

public enum MissionField {
    USER_ID("user_id"), ACTIVITY_ID("activity_id"), START_TIME("start_time"), END_TIME("end_time");

    private String columnName;

    MissionField(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }
}
