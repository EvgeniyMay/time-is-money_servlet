package com.mylearning.timeismoney.command.activity;

import com.mylearning.timeismoney.command.Command;
import com.mylearning.timeismoney.exception.PageNotFoundException;
import com.mylearning.timeismoney.service.ActivityService;

import javax.servlet.http.HttpServletRequest;

public class GetActivityEditCommand implements Command {

    private final ActivityService activityService;

    public GetActivityEditCommand(ActivityService activityService) {
        this.activityService = activityService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));

        try {
            request.setAttribute("activity", activityService.findById(id));
        } catch (Exception e) {
            throw new PageNotFoundException();
        }

        return "/WEB-INF/jsp/activity/activityEdit.jsp";
    }
}
