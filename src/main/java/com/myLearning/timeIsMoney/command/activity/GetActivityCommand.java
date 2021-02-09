package com.myLearning.timeIsMoney.command.activity;

import com.myLearning.timeIsMoney.command.Command;
import com.myLearning.timeIsMoney.dao.ActivityDao;

import javax.servlet.http.HttpServletRequest;

public class GetActivityCommand implements Command {

    private final ActivityDao activityDao;

    public GetActivityCommand(ActivityDao activityDao) {
        this.activityDao = activityDao;
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("activities", activityDao.findAll());

        return "/WEB-INF/jsp/activity/activityAll.jsp";
    }
}
