package com.mylearning.timeismoney.command.mission;

import com.mylearning.timeismoney.command.Command;
import com.mylearning.timeismoney.entity.Mission;
import com.mylearning.timeismoney.entity.enums.MissionState;
import com.mylearning.timeismoney.service.MissionService;

import javax.servlet.http.HttpServletRequest;

/**
 * accept mission command
 */
public class PostAcceptMissionCommand implements Command {

    private final MissionService missionService;

    public PostAcceptMissionCommand(MissionService missionService) {
        this.missionService = missionService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        boolean added = missionService.updateState(
                new Mission.Builder()
                        .id(Integer.parseInt(request.getParameter("missionId")))
                        .build(),
                MissionState.ACTIVE);

        if(!added) {
            request.setAttribute("addResult", "Mission is not actual now");
            PageableMissionUtil.fillPageableRequest(request,
                    MissionState.OFFERED, missionService);

            return "/WEB-INF/jsp/mission/missionOffered.jsp";
        }

        return "redirect:/app/mission/offered";
    }
}
