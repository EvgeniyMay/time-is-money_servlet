package com.mylearning.timeismoney.command.mission;

import com.mylearning.timeismoney.command.Command;
import com.mylearning.timeismoney.entity.Mission;
import com.mylearning.timeismoney.entity.enums.MissionState;
import com.mylearning.timeismoney.service.MissionService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

public class GetPassedMissionCommand implements Command {

    private final MissionService missionService;

    public GetPassedMissionCommand(MissionService missionService) {
        this.missionService = missionService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        List<Mission> missions = missionService.findAll();
        //ToDo
        // Make query
        request.setAttribute("missions", missions.stream()
                .filter(m -> MissionState.PASSED.equals(m.getState()))
                .collect(Collectors.toList()));

        return "/WEB-INF/jsp/mission/missionPassed.jsp";
    }
}
