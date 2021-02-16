package com.mylearning.timeismoney.command.activity;

import com.mylearning.timeismoney.command.Command;
import com.mylearning.timeismoney.exception.PageNotFoundException;
import com.mylearning.timeismoney.service.ActivityService;

import javax.servlet.http.HttpServletRequest;

public class GetArchivedActivityCommand implements Command {

    private final ActivityService activityService;

    public GetArchivedActivityCommand(ActivityService activityService) {
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

        int activityCount = activityService.getArchivedCount();
        int pageCount = (int)Math.ceil((double)activityCount/pageSize);

        request.setAttribute("activities", activityService.findArchivedPageable(curPage, pageSize));
        request.setAttribute("cur_page", curPage);
        request.setAttribute("page_count", pageCount);

        return "/WEB-INF/jsp/activity/activityArchived.jsp";
    }
}
