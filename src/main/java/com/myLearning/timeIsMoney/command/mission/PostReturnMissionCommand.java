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
        boolean returned = missionService.updateMissionState(new Mission.Builder()
                        .id(Integer.parseInt(request.getParameter("missionId")))
                        .build(),
                MissionState.ACTIVE);

        if(!returned) {
            request.setAttribute("addResult", "Mission is not actual now");
            request.setAttribute("missions", missionService.findByState(MissionState.OFFERED));
            return "/WEB-INF/jsp/mission/missionOffered.jsp";
        }

        return "redirect:/app/mission/passed";
    }
}
