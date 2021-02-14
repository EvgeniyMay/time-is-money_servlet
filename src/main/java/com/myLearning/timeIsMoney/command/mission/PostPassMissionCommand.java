package com.mylearning.timeismoney.command.mission;

import com.mylearning.timeismoney.command.Command;
import com.mylearning.timeismoney.entity.Mission;
import com.mylearning.timeismoney.entity.enums.MissionState;
import com.mylearning.timeismoney.service.MissionService;

import javax.servlet.http.HttpServletRequest;

public class PostPassMissionCommand implements Command {

    private final MissionService missionService;

    public PostPassMissionCommand(MissionService missionService) {
        this.missionService = missionService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Mission mission = new Mission();
        int missionId = Integer.parseInt(request.getParameter("missionId"));
        mission.setId(missionId);
        System.out.println(missionId);

        missionService.updateMissionState(mission, MissionState.PASSED);

        return "redirect:/app/profile";
    }
}
