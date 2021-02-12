package com.myLearning.timeIsMoney.command.user;

import com.myLearning.timeIsMoney.command.Command;
import com.myLearning.timeIsMoney.service.UserService;

import javax.servlet.http.HttpServletRequest;

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