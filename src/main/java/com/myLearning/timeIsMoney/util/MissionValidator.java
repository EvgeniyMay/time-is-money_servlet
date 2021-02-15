package com.mylearning.timeismoney.util;

import com.mylearning.timeismoney.entity.Mission;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MissionValidator {

    public static List<String> getErrors(Mission mission) {
        List<String> errors = new ArrayList<>();

        if(hasNull(errors, mission)) {
            return errors;
        }

        checkDuration(errors, mission);

        return errors;
    }

    private static boolean hasNull(List<String> errors, Mission mission) {
        if(Objects.isNull(mission.getStartTime()) || Objects.isNull(mission.getEndTime())) {
            errors.add("Please, fill all fields");
            return true;
        }
        return false;
    }

    private static void checkDuration(List<String> errors, Mission mission) {
        if(mission.getStartTime().compareTo(mission.getEndTime()) > 0) {
            errors.add("Duration cannot be less then 0");
        }
    }
}
