package com.mylearning.timeismoney.command.activity;

import com.mylearning.timeismoney.command.Command;
import com.mylearning.timeismoney.exception.PageNotFoundException;
import com.mylearning.timeismoney.service.ActivityService;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class GetActiveActivityCommand implements Command {

    private final ActivityService activityService;

    public GetActiveActivityCommand(ActivityService activityService) {
        this.activityService = activityService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int curPage = 0;
        int pageSize = 10;

        if (request.getParameter("cur_page") != null) {
            try {
                curPage = Integer.parseInt(request.getParameter("cur_page"));
            } catch (NumberFormatException e) {
                throw new PageNotFoundException();
            }
        }

        int activityCount = activityService.getActiveCount();
        int pageCount = (int)Math.ceil((double)activityCount/pageSize);

        request.setAttribute("activities", activityService.findActivePageable(curPage, pageSize));
        request.setAttribute("cur_page", curPage);
        request.setAttribute("page_count", pageCount);
        return "/WEB-INF/jsp/activity/activityActive.jsp";
    }
}
