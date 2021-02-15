package com.mylearning.timeismoney.util;

import com.mylearning.timeismoney.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserValidator {

    public static List<String> getErrors(User user) {
        List<String> errors = new ArrayList<>();

        checkLoginLength(errors, user.getLogin());

        checkPasswordLength(errors, user.getPassword());

        return errors;
    }

    private static void checkLoginLength(List<String> errors, String login) {
        if(Objects.isNull(login)) {
            errors.add("Length login error");
            return;
        }

        if(login.length() < 5 || login.length() > 20) {
            errors.add("Length login error. Between 5 and 20 characters");
        }
    }

    private static void checkPasswordLength(List<String> errors, String password) {
        if(Objects.isNull(password)) {
            errors.add("Length password error");
            return;
        }

        if(password.length() < 6 || password.length() > 20) {
            errors.add("Length password error. Between 6 and 20 characters");
        }
    }
}
