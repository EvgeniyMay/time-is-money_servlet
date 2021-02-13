package com.mylearning.timeismoney.entity.enums;

import java.util.Arrays;
import java.util.List;

public enum Role {

    GUEST(Arrays.asList(
            "/",
            "/login",
            "/registration"
    )),
    ADMIN(Arrays.asList(
            "/",
            "/profile",
            "/logout",
            "/user"
    )),
    USER(Arrays.asList(
            "/",
            "/profile",
            "/logout"
    ));

    private List<String> authorities;

    private Role(List<String> authorities) {
        this.authorities = authorities;
    }

    public List<String> getAuthorities() {
        return authorities;
    }
}
