package com.myLearning.timeIsMoney.command.activity;

import com.myLearning.timeIsMoney.command.Command;
import com.myLearning.timeIsMoney.service.ActivityService;

import javax.servlet.http.HttpServletRequest;

public class PostActivityDeleteCommand implements Command {

    private final ActivityService activityService;

    public PostActivityDeleteCommand(ActivityService activityService) {
        this.activityService = activityService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));

        activityService.deleteById(id);

        return "redirect:/app/activity";
    }
}
