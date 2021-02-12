package com.myLearning.timeIsMoney.command.mission;

import com.myLearning.timeIsMoney.command.Command;
import com.myLearning.timeIsMoney.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class GetMissionCommand implements Command {

    private final UserService userService;

    public GetMissionCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("users", userService.findAll());

        return "/WEB-INF/jsp/activity/activityAdd.jsp";
    }
}
