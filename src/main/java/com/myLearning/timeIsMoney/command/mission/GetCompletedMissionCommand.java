package com.mylearning.timeismoney.command.mission;

import com.mylearning.timeismoney.command.Command;
import com.mylearning.timeismoney.entity.enums.MissionState;
import com.mylearning.timeismoney.service.MissionService;

import javax.servlet.http.HttpServletRequest;

public class GetCompletedMissionCommand implements Command {

    private final MissionService missionService;

    public GetCompletedMissionCommand(MissionService missionService) {
        this.missionService = missionService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        GetMissionUtil.fillPageableRequest(request, MissionState.COMPLETED, missionService);

        return "/WEB-INF/jsp/mission/missionCompleted.jsp";
    }
}
