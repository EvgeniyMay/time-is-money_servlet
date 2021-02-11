package com.myLearning.timeIsMoney.command.activity;

import com.myLearning.timeIsMoney.command.Command;
import com.myLearning.timeIsMoney.entity.Activity;
import com.myLearning.timeIsMoney.service.ActivityService;

import javax.servlet.http.HttpServletRequest;

public class PostActivityCreateCommand implements Command {

    private final ActivityService activityService;

    public PostActivityCreateCommand(ActivityService activityService) {
        this.activityService = activityService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        Activity activity = new Activity();
        activity.setName(name);
        activity.setDescription(description);

        activityService.create(activity);

        return "redirect:/app/activity";
    }
}
