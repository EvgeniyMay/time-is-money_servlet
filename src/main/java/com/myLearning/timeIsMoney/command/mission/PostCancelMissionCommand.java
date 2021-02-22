package com.mylearning.timeismoney.command.mission;

import com.mylearning.timeismoney.command.Command;
import com.mylearning.timeismoney.entity.Mission;
import com.mylearning.timeismoney.entity.User;
import com.mylearning.timeismoney.entity.enums.MissionState;
import com.mylearning.timeismoney.entity.enums.Role;
import com.mylearning.timeismoney.service.MissionService;

import javax.servlet.http.HttpServletRequest;

public class PostCancelMissionCommand implements Command {

    private final MissionService missionService;

    public PostCancelMissionCommand(MissionService missionService) {
        this.missionService = missionService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("authUser");

        boolean canceled = missionService.cancel(
                user,
                new Mission.Builder()
                        .id(Integer.parseInt(request.getParameter("missionId")))
                        .build());

        if(!canceled) {
            request.setAttribute("addResult", "Mission is not actual now");
            PageableMissionUtil.fillPageableRequest(request, MissionState.OFFERED, missionService);

            return Role.ADMIN.equals(user.getRole()) ?
                    "/WEB-INF/jsp/mission/missionOffered.jsp"
                    : "redirect:/app/profile";
        }

        return Role.ADMIN.equals(user.getRole()) ?
                "redirect:/app/mission/offered"
                : "redirect:/app/profile";
    }
}
