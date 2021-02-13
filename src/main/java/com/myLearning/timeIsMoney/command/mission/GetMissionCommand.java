package com.mylearning.timeismoney.command.mission;

import com.mylearning.timeismoney.command.Command;
import com.mylearning.timeismoney.service.MissionService;
import com.mylearning.timeismoney.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class GetMissionCommand implements Command {

    private final MissionService missionService;

    public GetMissionCommand(MissionService missionService) {
        this.missionService = missionService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("missions", missionService.findAll());

        return "/WEB-INF/jsp/mission/missionAll.jsp";
    }
}
