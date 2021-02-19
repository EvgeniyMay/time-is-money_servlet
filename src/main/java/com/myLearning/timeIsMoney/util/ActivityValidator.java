package com.mylearning.timeismoney.util;


import com.mylearning.timeismoney.entity.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityValidator {

    public static List<String> getErrors(Activity activity) {
        List<String> errors = new ArrayList<>();

        if(hasNull(errors, activity)) {
            return errors;
        }

        return errors;
    }

    private static boolean hasNull(List<String> errors, Activity mission) {
        if(String.valueOf(mission.getName()).isEmpty()
                || String.valueOf(mission.getDescription()).isEmpty()) {
            //ToDo | Add localization
            errors.add("Please, fill all fields");
        }
        return false;
    }
}
