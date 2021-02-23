package com.mylearning.timeismoney.command.activity;

import com.mylearning.timeismoney.command.Command;
import com.mylearning.timeismoney.service.ActivityService;

import javax.servlet.http.HttpServletRequest;

public class PostArchiveActivityCommand implements Command {

    private final ActivityService activityService;

    public PostArchiveActivityCommand(ActivityService activityService) {
        this.activityService = activityService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        try {
            activityService.archiveById(Integer.parseInt(request.getParameter("id")));
        } catch (Exception e) {
            // ToDo | Log
        }

        return "redirect:/app/activity/active";
    }
}
