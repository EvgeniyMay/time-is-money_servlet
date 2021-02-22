package com.mylearning.timeismoney.command.mission;

import com.mylearning.timeismoney.command.Command;
import com.mylearning.timeismoney.entity.enums.MissionState;
import com.mylearning.timeismoney.service.MissionService;

import javax.servlet.http.HttpServletRequest;

public class GetOfferedMissionsCommand implements Command {

    private final MissionService missionService;

    public GetOfferedMissionsCommand(MissionService missionService) {
        this.missionService = missionService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        PageableMissionUtil.fillPageableRequest(request, MissionState.OFFERED, missionService);

        return "/WEB-INF/jsp/mission/missionOffered.jsp";
    }
}
