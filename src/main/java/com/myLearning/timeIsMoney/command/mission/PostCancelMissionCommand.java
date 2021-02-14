package com.mylearning.timeismoney.command.mission;

import com.mylearning.timeismoney.command.Command;
import com.mylearning.timeismoney.entity.Mission;
import com.mylearning.timeismoney.service.MissionService;

import javax.servlet.http.HttpServletRequest;

public class PostCancelMissionCommand implements Command {

    private final MissionService missionService;

    public PostCancelMissionCommand(MissionService missionService) {
        this.missionService = missionService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Mission mission = new Mission();
        int missionId = Integer.parseInt(request.getParameter("missionId"));
        mission.setId(missionId);

        missionService.delete(mission);

        return "redirect:/app/profile";
    }
}
