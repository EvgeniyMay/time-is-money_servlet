package com.mylearning.timeismoney.command.mission;

import com.mylearning.timeismoney.command.Command;
import com.mylearning.timeismoney.entity.Mission;
import com.mylearning.timeismoney.entity.enums.MissionState;
import com.mylearning.timeismoney.service.MissionService;

import javax.servlet.http.HttpServletRequest;

public class PostAcceptMissionCommand implements Command {

    private final MissionService missionService;

    public PostAcceptMissionCommand(MissionService missionService) {
        this.missionService = missionService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int missionId = Integer.parseInt(request.getParameter("missionId"));
        Mission mission = new Mission();
        mission.setId(missionId);

        missionService.updateMissionState(mission, MissionState.GIVEN);

        return "redirect:/app/mission/offered";
    }
}
