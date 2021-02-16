package com.mylearning.timeismoney.command.mission;

import com.mylearning.timeismoney.command.Command;
import com.mylearning.timeismoney.entity.Mission;
import com.mylearning.timeismoney.entity.enums.MissionField;
import com.mylearning.timeismoney.entity.enums.MissionState;
import com.mylearning.timeismoney.exception.PageNotFoundException;
import com.mylearning.timeismoney.service.MissionService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

public class GetActiveMissionCommand implements Command {

    private final MissionService missionService;

    public GetActiveMissionCommand(MissionService missionService) {
        this.missionService = missionService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int curPage = 0;
        int pageSize = 5;

        if (request.getParameter("curPage") != null) {
            try {
                curPage = Integer.parseInt(request.getParameter("curPage"));
            } catch (NumberFormatException e) {
                throw new PageNotFoundException();
            }
        }

        int activityCount = missionService.countByState(MissionState.ACTIVE);
        int pageCount = (int)Math.ceil((double)activityCount/pageSize);

        request.setAttribute("missions", missionService.findPageable(curPage, pageSize,
                MissionState.ACTIVE, MissionField.USER_ID));
        request.setAttribute("curPage", curPage);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("pageCount", pageCount);

        return "/WEB-INF/jsp/mission/missionActive.jsp";
    }
}
