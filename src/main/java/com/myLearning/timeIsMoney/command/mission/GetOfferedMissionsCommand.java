package com.mylearning.timeismoney.command.mission;

import com.mylearning.timeismoney.command.Command;
import com.mylearning.timeismoney.entity.Mission;
import com.mylearning.timeismoney.entity.enums.MissionState;
import com.mylearning.timeismoney.service.MissionService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

public class GetOfferedMissionsCommand implements Command {

    private final MissionService missionService;

    public GetOfferedMissionsCommand(MissionService missionService) {
        this.missionService = missionService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("missions", missionService.findByState(MissionState.OFFERED));

        return "/WEB-INF/jsp/mission/missionOffered.jsp";
    }
}
