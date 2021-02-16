package com.mylearning.timeismoney.command.activity;

import com.mylearning.timeismoney.command.Command;
import com.mylearning.timeismoney.entity.Activity;
import com.mylearning.timeismoney.service.ActivityService;

import javax.servlet.http.HttpServletRequest;

public class PostActivityCreateCommand implements Command {

    private final ActivityService activityService;

    public PostActivityCreateCommand(ActivityService activityService) {
        this.activityService = activityService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        activityService.create(new Activity.Builder()
                .name(request.getParameter("name"))
                .description(request.getParameter("description"))
                .build());

        return "redirect:/app/activity";
    }
}
