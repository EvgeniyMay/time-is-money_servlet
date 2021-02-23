package com.mylearning.timeismoney.command.mission;

import com.mylearning.timeismoney.command.Command;
import com.mylearning.timeismoney.entity.Mission;
import com.mylearning.timeismoney.entity.User;
import com.mylearning.timeismoney.entity.enums.MissionState;
import com.mylearning.timeismoney.service.ActivityService;
import com.mylearning.timeismoney.service.MissionService;

import javax.servlet.http.HttpServletRequest;

public class PostPassMissionCommand implements Command {

    private final MissionService missionService;

    public PostPassMissionCommand(MissionService missionService) {
        this.missionService = missionService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        missionService.pass((User) request.getSession().getAttribute("authUser"),
                new Mission.Builder()
                        .id(Integer.parseInt(request.getParameter("missionId")))
                        .build());

        return "redirect:/app/profile";

    }
}
