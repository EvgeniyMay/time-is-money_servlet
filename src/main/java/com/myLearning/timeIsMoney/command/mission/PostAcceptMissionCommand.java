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
        missionService.updateMissionState(new Mission.Builder()
                .id(Integer.parseInt(request.getParameter("missionId")))
                .build(),
                MissionState.ACTIVE);

        return "redirect:/app/mission/offered";
    }
}
