package com.mylearning.timeismoney.command.mission;

import com.mylearning.timeismoney.command.Command;
import com.mylearning.timeismoney.entity.Activity;
import com.mylearning.timeismoney.entity.Mission;
import com.mylearning.timeismoney.entity.User;
import com.mylearning.timeismoney.entity.enums.MissionState;
import com.mylearning.timeismoney.service.MissionService;
import com.mylearning.timeismoney.util.HtmlDataConverter;
import com.mylearning.timeismoney.util.MissionValidator;

import javax.servlet.http.HttpServletRequest;
import java.time.format.DateTimeParseException;
import java.util.Collections;

/**
 * offer mission command
 */
public class PostOfferMissionCommand implements Command {

    private final MissionService missionService;

    public PostOfferMissionCommand(MissionService missionService) {
        this.missionService = missionService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Mission mission;
        try {
            mission = new Mission.Builder()
                    .user((User)request.getSession().getAttribute("authUser"))
                    .activity(new Activity.Builder().id(Integer.parseInt(request.getParameter("activityId"))).build())
                    .state(MissionState.OFFERED)
                    .startTime(HtmlDataConverter.parseToLocalDateTime(request.getParameter("startTime")))
                    .endTime(HtmlDataConverter.parseToLocalDateTime(request.getParameter("endTime")))
                    .build();
        } catch (DateTimeParseException | NumberFormatException e) {
            request.setAttribute("errors",
                    Collections.singletonList("Please, fill all fields"));
            request.setAttribute("activities", missionService.getUsersAndActivities().getActivities());

            return "/WEB-INF/jsp/mission/missionOffer.jsp";
        }

        if(!MissionValidator.getErrors(mission).isEmpty()) {
            request.setAttribute("errors", MissionValidator.getErrors(mission));
            request.setAttribute("activities", missionService.getUsersAndActivities().getActivities());

            return "/WEB-INF/jsp/mission/missionOffer.jsp";
        }

        missionService.create(mission);

        return "redirect:/app/profile";
    }
}
