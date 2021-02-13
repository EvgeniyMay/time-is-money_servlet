package com.mylearning.timeismoney.command.activity;

import com.mylearning.timeismoney.command.Command;
import com.mylearning.timeismoney.service.ActivityService;

import javax.servlet.http.HttpServletRequest;

public class GetActivityCommand implements Command {

    private final ActivityService activityService;

    public GetActivityCommand(ActivityService activityService) {
        this.activityService = activityService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("activities", activityService.findAll());

        return "/WEB-INF/jsp/activity/activityAll.jsp";
    }
}
