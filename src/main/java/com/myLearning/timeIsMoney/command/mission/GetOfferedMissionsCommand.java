package com.mylearning.timeismoney.command.mission;

import com.mylearning.timeismoney.command.Command;
import com.mylearning.timeismoney.entity.enums.MissionState;
import com.mylearning.timeismoney.service.MissionService;

import javax.servlet.http.HttpServletRequest;

/**
 * all missions with status OFFERED with pagination page
 */
public class GetOfferedMissionsCommand implements Command {

    private final MissionService missionService;

    public GetOfferedMissionsCommand(MissionService missionService) {
        this.missionService = missionService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        /**
         * fill page by mission state
         * @see com.mylearning.timeismoney.command.mission.PageableMissionUtil
         */
        PageableMissionUtil.fillPageableRequest(request, MissionState.OFFERED, missionService);

        return "/WEB-INF/jsp/mission/missionOffered.jsp";
    }
}
