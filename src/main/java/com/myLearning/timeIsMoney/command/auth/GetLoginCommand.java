package com.mylearning.timeismoney.command.auth;

import com.mylearning.timeismoney.command.Command;

import javax.servlet.http.HttpServletRequest;

/**
 * login form page
 */
public class GetLoginCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/jsp/auth/login.jsp";
    }
}
