package com.mylearning.timeismoney.command.user;

import com.mylearning.timeismoney.command.Command;

import javax.servlet.http.HttpServletRequest;

public class GetUserProfile implements Command {

    @Override
    public String execute(HttpServletRequest request) {

        return "/WEB-INF/jsp/user/userProfile.jsp";
    }
}
