package com.mylearning.timeismoney.command.mission;

import com.mylearning.timeismoney.command.Command;
import com.mylearning.timeismoney.entity.Mission;
import com.mylearning.timeismoney.entity.User;
import com.mylearning.timeismoney.entity.enums.MissionState;
import com.mylearning.timeismoney.service.ActivityService;
import com.mylearning.timeismoney.service.MissionService;

import javax.servlet.http.HttpServletRequest;

public class PostPassMissionCommand implements Command {

    private final MissionService missionService;

    public PostPassMissionCommand(MissionService missionService) {
        this.missionService = missionService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Mission mission = new Mission.Builder()
                .id(Integer.parseInt(request.getParameter("missionId")))
                .build();

        User user = (User) request.getSession().getAttribute("authUser");
        missionService.userUpdateMissionState(user, mission, MissionState.PASSED);

        return "redirect:/app/profile";

    }
}
