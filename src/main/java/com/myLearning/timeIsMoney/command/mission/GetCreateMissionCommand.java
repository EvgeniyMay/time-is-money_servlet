package com.mylearning.timeismoney.command.mission;

import com.mylearning.timeismoney.command.Command;
import com.mylearning.timeismoney.service.MissionService;

import javax.servlet.http.HttpServletRequest;

public class GetCreateMissionCommand implements Command {

    private final MissionService missionService;

    public GetCreateMissionCommand(MissionService missionService) {
        this.missionService = missionService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("usersAndActivities", missionService.getUsersAndActivities());

        return "/WEB-INF/jsp/mission/missionCreate.jsp";
    }
}
