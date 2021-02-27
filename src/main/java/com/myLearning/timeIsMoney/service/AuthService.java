package com.mylearning.timeismoney.service;

import com.mylearning.timeismoney.entity.User;
import com.mylearning.timeismoney.entity.enums.Role;
import com.mylearning.timeismoney.exception.AuthorizationException;
import com.mylearning.timeismoney.exception.PageNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AuthService {

    private static AuthService authService;

    private final List<Integer> authedUsers = new ArrayList<>();

    private AuthService() { }

    public static AuthService getInstance() {
        if(authService == null) {
            synchronized (AuthService.class) {
                if(authService == null) {
                    authService = new AuthService();
                }
            }
        }
        return authService;
    }

    public boolean authUser(User user) {
        if(authedUsers.contains(user.getId()))
            return false;

        return authedUsers.add(user.getId());
    }

    public boolean dropUser(User user) {
        // To delete by object, not by index
        authedUsers.remove(new Integer(user.getId()));

        return true;
    }

    public boolean checkUserAccess(User user, String path) {
        // Guest
        if(Objects.isNull(user)) {
            if(!Role.GUEST.getAuthorities().contains(path)) {
                throw new AuthorizationException();
            }
            return true;
        }
        // Not guest
        if(!user.getRole().getAuthorities().contains(path)) {
            throw new PageNotFoundException();
        }
        return true;
    }
}
