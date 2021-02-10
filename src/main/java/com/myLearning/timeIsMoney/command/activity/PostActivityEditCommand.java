package com.myLearning.timeIsMoney.command.activity;

import com.myLearning.timeIsMoney.command.Command;
import com.myLearning.timeIsMoney.entity.Activity;
import com.myLearning.timeIsMoney.service.ActivityService;

import javax.servlet.http.HttpServletRequest;

public class PostActivityEditCommand implements Command {

    private final ActivityService activityService;

    public PostActivityEditCommand(ActivityService activityService) {
        this.activityService = activityService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        Activity activity = new Activity();
        activity.setId(id);
        activity.setName(name);
        activity.setDescription(description);

        activityService.update(activity);

        return "redirect:/app/activity";
    }
}
