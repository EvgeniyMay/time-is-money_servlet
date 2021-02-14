package com.mylearning.timeismoney.command.mission;

import com.mylearning.timeismoney.command.Command;
import com.mylearning.timeismoney.service.ActivityService;

import javax.servlet.http.HttpServletRequest;

public class GetOfferMission implements Command {

    private final ActivityService activityService;

    public GetOfferMission(ActivityService activityService) {
        this.activityService = activityService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("activities", activityService.findAll());

        return "/WEB-INF/jsp/mission/missionOffer.jsp";
    }
}
