package com.mylearning.timeismoney.command.activity;

import com.mylearning.timeismoney.command.Command;
import com.mylearning.timeismoney.entity.Activity;
import com.mylearning.timeismoney.exception.ServiceLogicException;
import com.mylearning.timeismoney.service.ActivityService;
import com.mylearning.timeismoney.util.ActivityValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * edit activity command
 */
public class PostEditActivityCommand implements Command {

    private final ActivityService activityService;

    public PostEditActivityCommand(ActivityService activityService) {
        this.activityService = activityService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Activity activity = new Activity.Builder()
                .id(Integer.parseInt(request.getParameter("id")))
                .name(request.getParameter("name"))
                .description(request.getParameter("description"))
                .build();

        if(!ActivityValidator.getErrors(activity).isEmpty()) {
            request.setAttribute("activity",activity);
            request.setAttribute("errors", ActivityValidator.getErrors(activity));

            return "/WEB-INF/jsp/activity/activityEdit.jsp";
        }

        try {
            activityService.update(activity);
        } catch (ServiceLogicException e) {
            request.setAttribute("activity",(activity));

            //ToDo | Localization
            request.setAttribute("errors", Arrays.asList("Update error"));
            return "/WEB-INF/jsp/activity/activityEdit.jsp";
        }
        
        return "redirect:/app/activity/active";
    }
}
