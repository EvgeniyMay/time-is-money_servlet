package com.mylearning.timeismoney.command.activity;

import com.mylearning.timeismoney.command.Command;
import com.mylearning.timeismoney.exception.ServiceLogicException;
import com.mylearning.timeismoney.service.ActivityService;

import javax.servlet.http.HttpServletRequest;

/**
 * archiving activity command
 */
public class PostArchiveActivityCommand implements Command {

    private final ActivityService activityService;

    public PostArchiveActivityCommand(ActivityService activityService) {
        this.activityService = activityService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        try {
            activityService.archiveById(Integer.parseInt(request.getParameter("id")));
        } catch (ServiceLogicException e) {
            // ToDo | Localize
            /**
             * if something went wrong
             * we just add error message
             * and delegate get command executing
             */
            request.setAttribute("error", "Archive error");

            return new GetActiveActivityCommand(activityService)
                    .execute(request);
        }

        return "redirect:/app/activity/active";
    }
}
