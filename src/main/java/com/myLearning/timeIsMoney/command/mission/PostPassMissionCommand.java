package com.mylearning.timeismoney.command.mission;

import com.mylearning.timeismoney.command.Command;
import com.mylearning.timeismoney.entity.Mission;
import com.mylearning.timeismoney.entity.User;
import com.mylearning.timeismoney.entity.enums.MissionState;
import com.mylearning.timeismoney.service.ActivityService;
import com.mylearning.timeismoney.service.MissionService;

import javax.servlet.http.HttpServletRequest;

/**
 * pass mission command
 */
public class PostPassMissionCommand implements Command {

    private final MissionService missionService;

    public PostPassMissionCommand(MissionService missionService) {
        this.missionService = missionService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("authUser");
        Mission mission = new Mission.Builder()
                .id(Integer.parseInt(request.getParameter("missionId")))
                .build();

        try {
            missionService.pass(user, mission);
        } catch (Exception e) {
            // ToDo | Log
        }

        return "redirect:/app/profile";
    }
}
