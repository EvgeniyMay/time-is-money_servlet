package com.mylearning.timeismoney.command.mission;

import com.mylearning.timeismoney.command.Command;
import com.mylearning.timeismoney.entity.Mission;
import com.mylearning.timeismoney.entity.enums.MissionState;
import com.mylearning.timeismoney.service.MissionService;

import javax.servlet.http.HttpServletRequest;

public class PostReturnMissionCommand implements Command {

    private final MissionService missionService;

    public PostReturnMissionCommand(MissionService missionService) {
        this.missionService = missionService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Mission mission = new Mission.Builder()
                .id(Integer.parseInt(request.getParameter("missionId")))
                .build();

        boolean returned = missionService.updateState(mission, MissionState.ACTIVE);

        if(!returned) {
            request.setAttribute("addResult", "Mission is not actual now");
            PageableMissionUtil.fillPageableRequest(request, MissionState.OFFERED, missionService);
            return "/WEB-INF/jsp/mission/missionPassed.jsp";
        }

        return "redirect:" + request.getHeader("referer");
    }
}
