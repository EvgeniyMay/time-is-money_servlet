package com.mylearning.timeismoney.command.user;

import com.mylearning.timeismoney.command.Command;
import com.mylearning.timeismoney.entity.Mission;
import com.mylearning.timeismoney.entity.User;
import com.mylearning.timeismoney.entity.enums.MissionState;
import com.mylearning.timeismoney.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

public class GetUserProfile implements Command {

    private final UserService userService;
    public GetUserProfile(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute("authUser");
        List<Mission> missions = userService.findById(user.getId()).getMissions();

        request.setAttribute("activeMissions", missions.stream()
                .filter(m -> MissionState.ACTIVE.equals(m.getState()))
                .collect(Collectors.toList()));

        request.setAttribute("passedMissions", missions.stream()
                .filter(m -> MissionState.PASSED.equals(m.getState()))
                .collect(Collectors.toList()));

        request.setAttribute("offeredMissions", missions.stream()
                .filter(m -> MissionState.OFFERED.equals(m.getState()))
                .collect(Collectors.toList()));


        return "/WEB-INF/jsp/user/userProfile.jsp";
    }
}
