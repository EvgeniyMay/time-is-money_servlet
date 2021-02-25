package com.mylearning.timeismoney.command.activity;

import com.mylearning.timeismoney.command.Command;
import com.mylearning.timeismoney.exception.ServiceLogicException;
import com.mylearning.timeismoney.service.ActivityService;

import javax.servlet.http.HttpServletRequest;

/**
 * activation activity command
 */
public class PostActivateActivityCommand implements Command {

    private final ActivityService activityService;

    public PostActivateActivityCommand(ActivityService activityService) {
        this.activityService = activityService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        try {
            activityService.activateById(Integer.parseInt(request.getParameter("id")));
        } catch (ServiceLogicException e) {
            // ToDo | Log
            request.setAttribute("error", "Activate error");

            return new GetArchivedActivityCommand(activityService)
                    .execute(request);
        }

        return "redirect:/app/activity/archived";
    }
}
