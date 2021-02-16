package com.mylearning.timeismoney.command.activity;

import com.mylearning.timeismoney.command.Command;
import com.mylearning.timeismoney.service.ActivityService;

import javax.servlet.http.HttpServletRequest;

public class PostActivityDeleteCommand implements Command {

    private final ActivityService activityService;

    public PostActivityDeleteCommand(ActivityService activityService) {
        this.activityService = activityService;
    }

    @Override
    public String execute(HttpServletRequest request) {
         activityService.deleteById(Integer.parseInt(request.getParameter("id")));

        return "redirect:/app/activity";
    }
}
