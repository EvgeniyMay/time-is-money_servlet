package com.myLearning.timeIsMoney.command.activity;

import com.myLearning.timeIsMoney.command.Command;
import com.myLearning.timeIsMoney.dao.ActivityDao;
import com.myLearning.timeIsMoney.dao.DaoFactory;
import com.myLearning.timeIsMoney.dao.impl.JdbcConnectionPool;
import com.myLearning.timeIsMoney.dao.impl.JdbcDaoFactory;

import javax.servlet.http.HttpServletRequest;

public class PostActivityDeleteCommand implements Command {

    //ToDo
    // Replace
    private final DaoFactory daoFactory = JdbcDaoFactory.getInstance();
    private final ActivityDao activityDao = daoFactory.createActivityDao(JdbcConnectionPool.getInstance());

    @Override
    public String execute(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));

        activityDao.deleteById(id);

        return "redirect:/app/activity";
    }
}
