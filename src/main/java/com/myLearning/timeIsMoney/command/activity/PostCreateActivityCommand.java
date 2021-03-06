package com.mylearning.timeismoney.command.activity;

import com.mylearning.timeismoney.command.Command;
import com.mylearning.timeismoney.entity.Activity;
import com.mylearning.timeismoney.exception.ServiceLogicException;
import com.mylearning.timeismoney.service.ActivityService;
import com.mylearning.timeismoney.util.ActivityValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * creating activity command
 */
public class PostCreateActivityCommand implements Command {

    private final ActivityService activityService;

    public PostCreateActivityCommand(ActivityService activityService) {
        this.activityService = activityService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Activity activity = new Activity.Builder()
                .name(request.getParameter("name"))
                .description(request.getParameter("description"))
                .build();

        if(!ActivityValidator.getErrors(activity).isEmpty()) {
            request.setAttribute("errors", ActivityValidator.getErrors(activity));
            return "/WEB-INF/jsp/activity/activityAdd.jsp";
        }

        try {
            activityService.create(activity);
        } catch (ServiceLogicException e) {
            //ToDo | Localization
            request.setAttribute("errors", Arrays.asList("Activity creation error"));
            return "/WEB-INF/jsp/activity/activityAdd.jsp";
        }

        return "redirect:/app/activity/active";
    }
}
