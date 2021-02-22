package com.mylearning.timeismoney.util;

import com.mylearning.timeismoney.entity.User;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserValidatorTest {

    @Test
    public void emptyFields() {
        User user = new User();

        assertFalse(UserValidator.getErrors(user).isEmpty());
    }

    @Test
    public void emptyName() {
        User user = new User.Builder()
                .password("test")
                .build();

        assertFalse(UserValidator.getErrors(user).isEmpty());
    }

    @Test
    public void emptyPassword() {
        User user = new User.Builder()
                .login("test")
                .build();

        assertFalse(UserValidator.getErrors(user).isEmpty());
    }

    @Test
    public void notEmptyFields() {
        User user = new User.Builder()
                .login("testTest")
                .password("testTest")
                .build();

        assertTrue(UserValidator.getErrors(user).isEmpty());
    }

}