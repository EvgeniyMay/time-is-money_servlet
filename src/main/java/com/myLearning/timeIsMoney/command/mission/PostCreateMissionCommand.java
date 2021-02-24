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
 * create mission command
 */
public class PostCreateMissionCommand implements Command {

    private final MissionService missionService;

    public PostCreateMissionCommand(MissionService missionService) {
        this.missionService = missionService;
    }


    @Override
    public String execute(HttpServletRequest request) {
        Mission mission;
        try {
            mission = new Mission.Builder()
                    .user(new User.Builder().id(Integer.parseInt(request.getParameter("userId"))).build())
                    .activity(new Activity.Builder().id(Integer.parseInt(request.getParameter("activityId"))).build())
                    .state(MissionState.ACTIVE)
                    .startTime(HtmlDataConverter.parseToLocalDateTime(request.getParameter("startTime")))
                    .endTime(HtmlDataConverter.parseToLocalDateTime(request.getParameter("endTime")))
                    .build();
        } catch (DateTimeParseException | NumberFormatException e) {
            request.setAttribute("errors", Collections.singletonList("Please, fill all fields"));
            request.setAttribute("usersAndActivities", missionService.getUsersAndActivities());

            return "/WEB-INF/jsp/mission/missionCreate.jsp";
        }

        if(!MissionValidator.getErrors(mission).isEmpty()) {
            request.setAttribute("errors", MissionValidator.getErrors(mission));
            request.setAttribute("usersAndActivities", missionService.getUsersAndActivities());

            return "/WEB-INF/jsp/mission/missionCreate.jsp";
        }

        missionService.create(mission);

        return "redirect:/app/mission/active";
    }
}
