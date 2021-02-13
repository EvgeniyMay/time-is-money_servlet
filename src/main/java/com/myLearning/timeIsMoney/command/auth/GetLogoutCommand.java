package com.mylearning.timeismoney.command.auth;

import com.mylearning.timeismoney.command.Command;

import javax.servlet.http.HttpServletRequest;

public class GetLogoutCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/jsp/auth/logout.jsp";
    }
}
