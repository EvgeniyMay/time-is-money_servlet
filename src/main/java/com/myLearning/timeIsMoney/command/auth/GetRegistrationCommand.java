package com.mylearning.timeismoney.command.auth;

import com.mylearning.timeismoney.command.Command;

import javax.servlet.http.HttpServletRequest;

/**
 * registration form page
 */
public class GetRegistrationCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/jsp/auth/registration.jsp";
    }
}
