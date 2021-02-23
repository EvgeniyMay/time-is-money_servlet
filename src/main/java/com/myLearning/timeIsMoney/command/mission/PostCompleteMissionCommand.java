package com.mylearning.timeismoney.command.mission;

import com.mylearning.timeismoney.command.Command;
import com.mylearning.timeismoney.entity.Mission;
import com.mylearning.timeismoney.entity.enums.MissionState;
import com.mylearning.timeismoney.service.MissionService;

import javax.servlet.http.HttpServletRequest;

public class PostCompleteMissionCommand implements Command {

    private final MissionService missionService;

    public PostCompleteMissionCommand(MissionService missionService) {
        this.missionService = missionService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Mission mission = new Mission.Builder()
                .id(Integer.parseInt(request.getParameter("missionId")))
                .build();

        missionService.updateState(mission, MissionState.COMPLETED);

        return "redirect:" + request.getHeader("referer");
    }
}
