package com.myLearning.timeIsMoney.command.auth;

import com.myLearning.timeIsMoney.command.Command;

import javax.servlet.http.HttpServletRequest;

public class GetRegistrationCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/jsp/auth/registration.jsp";
    }
}
