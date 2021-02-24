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
        /**
         * fill page by mission state
         * @see com.mylearning.timeismoney.command.mission.PageableMissionUtil
         */
        request.setAttribute("usersAndActivities", missionService.getUsersAndActivities());

        return "/WEB-INF/jsp/mission/missionCreate.jsp";
    }
}
