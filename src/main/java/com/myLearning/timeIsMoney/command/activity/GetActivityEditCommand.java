package com.myLearning.timeIsMoney.command.activity;

import com.myLearning.timeIsMoney.command.Command;
import com.myLearning.timeIsMoney.dao.JdbcActivityDao;

import javax.servlet.http.HttpServletRequest;

public class GetActivityEditCommand implements Command {

    //ToDo
    // Replace
    private final JdbcActivityDao jdbcActivityDao = new JdbcActivityDao();

    @Override
    public String execute(HttpServletRequest request) {
        System.out.println("Get Edit !!!!!!!!!");
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println(id);

        request.setAttribute("activity", jdbcActivityDao.findById(id));

        return "/WEB-INF/jsp/activity/activityEdit.jsp";
    }
}
