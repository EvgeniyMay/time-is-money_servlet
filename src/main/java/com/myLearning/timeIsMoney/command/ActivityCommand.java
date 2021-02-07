package com.myLearning.timeIsMoney.command;

import com.myLearning.timeIsMoney.dao.JdbcActivityDao;

import javax.servlet.http.HttpServletRequest;

public class ActivityCommand implements Command {

    private final JdbcActivityDao jdbcActivityDao = new JdbcActivityDao();

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("activities", jdbcActivityDao.findAll());

        return "/WEB-INF/jsp/activity/activityAll.jsp";
    }
}
