package com.mylearning.timeismoney.command.mission;

import com.mylearning.timeismoney.command.Command;
import com.mylearning.timeismoney.entity.Activity;
import com.mylearning.timeismoney.entity.Mission;
import com.mylearning.timeismoney.entity.User;
import com.mylearning.timeismoney.entity.enums.MissionState;
import com.mylearning.timeismoney.service.MissionService;
import com.mylearning.timeismoney.util.HtmlDataConverter;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

public class PostOfferMission implements Command {

    private final MissionService missionService;

    public PostOfferMission(MissionService missionService) {
        this.missionService = missionService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Mission mission = new Mission();

        User user = (User)request.getSession().getAttribute("authUser");
        mission.setUser(user);

        mission.setState(MissionState.OFFERED);

        Activity activity = new Activity();
        activity.setId(Integer.parseInt(request.getParameter("activityId")));
        mission.setActivity(activity);

        LocalDateTime startTime = HtmlDataConverter.parseToLocalDateTime(request.getParameter("startTime"));
        LocalDateTime endTime = HtmlDataConverter.parseToLocalDateTime(request.getParameter("endTime"));
        mission.setStartTime(startTime);
        mission.setEndTime(endTime);

        missionService.createMission(mission);

        return "redirect:/app/profile";
    }
}
