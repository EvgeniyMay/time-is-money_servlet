package com.myLearning.timeIsMoney.command.activity;

import com.myLearning.timeIsMoney.command.Command;
import com.myLearning.timeIsMoney.dao.JdbcActivityDao;

import javax.servlet.http.HttpServletRequest;

public class GetActivityCommand implements Command {

    //ToDo
    // Replace
    private final JdbcActivityDao jdbcActivityDao = new JdbcActivityDao();

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("activities", jdbcActivityDao.findAll());

        return "/WEB-INF/jsp/activity/activityAll.jsp";
    }
}
