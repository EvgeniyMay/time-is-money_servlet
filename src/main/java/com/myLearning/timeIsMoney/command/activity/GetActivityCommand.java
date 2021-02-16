package com.mylearning.timeismoney.command.activity;

import com.mylearning.timeismoney.command.Command;
import com.mylearning.timeismoney.service.ActivityService;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class GetActivityCommand implements Command {

    private final ActivityService activityService;

    public GetActivityCommand(ActivityService activityService) {
        this.activityService = activityService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int curPage = 0;
        int pageSize = 7;

        if (request.getParameter("curPage") != null) {
            try {
                curPage = Integer.parseInt(request.getParameter("curPage"));
            } catch (NumberFormatException e) {
                curPage = 0;
            }
        }


        int activityCount = activityService.getCount();
        int pageCount = (int)Math.ceil((double)activityCount/pageSize);

        request.setAttribute("activities", activityService.findPageable(curPage, pageSize));
        request.setAttribute("curPage", curPage);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("pageCount", pageCount);
        return "/WEB-INF/jsp/activity/activityAll.jsp";
    }
}
