package com.mylearning.timeismoney.command.user;

import com.mylearning.timeismoney.command.Command;
import com.mylearning.timeismoney.service.UserService;

import javax.servlet.http.HttpServletRequest;

/**
 * all active users list page
 */
public class GetUserCommand implements Command {

    private final UserService userService;
    public GetUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("users", userService.findAll());

        return "/WEB-INF/jsp/user/userAll.jsp";
    }
}
