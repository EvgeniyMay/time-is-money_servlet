package com.myLearning.timeIsMoney.command.activity;

import com.myLearning.timeIsMoney.command.Command;
import com.myLearning.timeIsMoney.service.ActivityService;

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
