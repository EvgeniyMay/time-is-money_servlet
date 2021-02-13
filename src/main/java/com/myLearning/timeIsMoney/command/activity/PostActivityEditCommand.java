package com.mylearning.timeismoney.command.activity;

import com.mylearning.timeismoney.command.Command;
import com.mylearning.timeismoney.entity.Activity;
import com.mylearning.timeismoney.service.ActivityService;

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
