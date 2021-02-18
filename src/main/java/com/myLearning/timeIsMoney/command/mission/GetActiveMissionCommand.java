package com.mylearning.timeismoney.command.mission;

import com.mylearning.timeismoney.command.Command;
import com.mylearning.timeismoney.entity.enums.MissionState;
import com.mylearning.timeismoney.service.MissionService;

import javax.servlet.http.HttpServletRequest;

public class GetActiveMissionCommand implements Command {

    private final MissionService missionService;

    public GetActiveMissionCommand(MissionService missionService) {
        this.missionService = missionService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        GetMissionUtil.makeExecuteByState(request, MissionState.ACTIVE, missionService);

        return "/WEB-INF/jsp/mission/missionActive.jsp";
    }
}
