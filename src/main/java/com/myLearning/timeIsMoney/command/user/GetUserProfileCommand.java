package com.mylearning.timeismoney.command.user;

import com.mylearning.timeismoney.command.Command;
import com.mylearning.timeismoney.entity.Mission;
import com.mylearning.timeismoney.entity.User;
import com.mylearning.timeismoney.entity.enums.MissionState;
import com.mylearning.timeismoney.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * personal profile of user with all his missions
 * and form for offering
 */
public class GetUserProfileCommand implements Command {

    private final UserService userService;
    public GetUserProfileCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute("authUser");
        List<Mission> missions = userService.findById(user.getId()).getMissions();

        request.setAttribute("activeMissions",
                getMissionsByState(missions, MissionState.ACTIVE));

        request.setAttribute("passedMissions",
                getMissionsByState(missions, MissionState.PASSED));

        request.setAttribute("offeredMissions",
                getMissionsByState(missions, MissionState.OFFERED));

        return "/WEB-INF/jsp/user/userProfile.jsp";
    }

    private List<Mission> getMissionsByState(List<Mission> missions, MissionState state){
        return missions.stream()
                .filter(m -> state.equals(m.getState()))
                .collect(Collectors.toList());
    }
}
