package com.mylearning.timeismoney.command.mission;

import com.mylearning.timeismoney.command.Command;
import com.mylearning.timeismoney.entity.Mission;
import com.mylearning.timeismoney.entity.enums.MissionField;
import com.mylearning.timeismoney.entity.enums.MissionState;
import com.mylearning.timeismoney.exception.PageNotFoundException;
import com.mylearning.timeismoney.service.MissionService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class GetCompletedMissionCommand implements Command {

    private final MissionService missionService;

    public GetCompletedMissionCommand(MissionService missionService) {
        this.missionService = missionService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int curPage = 0;
        int pageSize = 5;
        MissionField sortField = MissionField.ACTIVITY_ID;

        if (request.getParameter("cur_page") != null) {
            try {
                curPage = Integer.parseInt(request.getParameter("cur_page"));
            } catch (NumberFormatException e) {
                throw new PageNotFoundException();
            }
        }

        if(!Objects.isNull(request.getParameter("sort_field"))) {
            try {
                sortField = MissionField.valueOf(request.getParameter("sort_field").toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new PageNotFoundException();
            }
        }

        int activityCount = missionService.countByState(MissionState.COMPLETED);
        int pageCount = (int)Math.ceil((double)activityCount/pageSize);

        request.setAttribute("missions", missionService.findPageable(curPage, pageSize, MissionState.COMPLETED, sortField));
        request.setAttribute("sort_field", sortField.toString().toLowerCase());
        request.setAttribute("page_count", pageCount);

        return "/WEB-INF/jsp/mission/missionCompleted.jsp";
    }
}
