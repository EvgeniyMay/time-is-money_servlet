package com.mylearning.timeismoney.util;

import com.mylearning.timeismoney.dto.UserDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserValidator {

    public static List<String> getErrors(UserDto userDto) {
        List<String> errors = new ArrayList<>();

        checkLoginLength(errors, userDto.getLogin());

        checkPasswordLength(errors, userDto.getPassword());

        return errors;
    }

    private static void checkLoginLength(List<String> errors, String login) {
        if(Objects.isNull(login)) {
            errors.add("Length login error");
            return;
        }

        if(login.length() < 4 || login.length() > 20) {
            errors.add("Length login error");
        }
    }

    private static void checkPasswordLength(List<String> errors, String password) {
        if(Objects.isNull(password)) {
            errors.add("Length password error");
            return;
        }

        if(password.length() < 6 || password.length() > 20) {
            errors.add("Length password error");
        }
    }

}
