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
            "/user",

            "/activity",
            "/activity/add",
            "/activity/edit",
            "/activity/delete",

            "/mission",
            "/mission/add"
    )),
    USER(Arrays.asList(
            "/",
            "/profile",
            "/logout",

            "/mission/offer",
            "/mission/pass"
    ));

    private List<String> authorities;

    private Role(List<String> authorities) {
        this.authorities = authorities;
    }

    public List<String> getAuthorities() {
        return authorities;
    }
}
