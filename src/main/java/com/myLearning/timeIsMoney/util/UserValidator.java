package com.myLearning.timeIsMoney.util;

import com.myLearning.timeIsMoney.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

public class UserValidator {

    public static List<String> getErrors(UserDto userDto) {
        List<String> errors = new ArrayList<>();

        checkLoginLength(errors, userDto.getLogin());

        checkPasswordLength(errors, userDto.getPassword());

        return errors;
    }

    private static void checkLoginLength(List<String> errors, String login) {
        if(login == null) {
            errors.add("Length login error");
            return;
        }

        if(login.length() < 4 || login.length() > 20) {
            errors.add("Length login error");
        }
    }

    private static void checkPasswordLength(List<String> errors, String password) {
        if(password == null) {
            errors.add("Length password error");
            return;
        }

        if(password.length() < 6 || password.length() > 20) {
            errors.add("Length password error");
        }
    }

}
