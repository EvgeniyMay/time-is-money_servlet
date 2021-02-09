package com.myLearning.timeIsMoney.command.activity;

import com.myLearning.timeIsMoney.command.Command;
import com.myLearning.timeIsMoney.dao.ActivityDao;

import javax.servlet.http.HttpServletRequest;

public class GetActivityEditCommand implements Command {

    private final ActivityDao activityDao;

    public GetActivityEditCommand(ActivityDao activityDao) {
        this.activityDao = activityDao;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("activity", activityDao.findById(id));

        return "/WEB-INF/jsp/activity/activityEdit.jsp";
    }
}
