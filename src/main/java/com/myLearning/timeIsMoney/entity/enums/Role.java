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

            "/activity/active",
            "/activity/add",
            "/activity/edit",
            "/activity/archive",
            "/activity/activate",
            "/activity/archived",

            "/mission/add",
            "/mission/accept",
            "/mission/cancel",
            "/mission/complete",
            "/mission/return",
            "/mission/offered",
            "/mission/active",
            "/mission/passed",
            "/mission/completed",
            "/mission/offer",
            "/mission/pass"
    )),
    USER(Arrays.asList(
            "/",
            "/profile",
            "/logout",

            "/mission/offer",
            "/mission/pass",
            "/mission/cancel"
    ));

    private List<String> authorities;

    private Role(List<String> authorities) {
        this.authorities = authorities;
    }

    public List<String> getAuthorities() {
        return authorities;
    }
}
