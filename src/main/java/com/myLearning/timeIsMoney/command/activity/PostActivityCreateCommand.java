package com.myLearning.timeIsMoney.command.activity;

import com.myLearning.timeIsMoney.command.Command;
import com.myLearning.timeIsMoney.dao.ActivityDao;
import com.myLearning.timeIsMoney.dao.DaoFactory;
import com.myLearning.timeIsMoney.dao.impl.JdbcActivityDao;
import com.myLearning.timeIsMoney.dao.impl.JdbcConnectionPool;
import com.myLearning.timeIsMoney.dao.impl.JdbcDaoFactory;
import com.myLearning.timeIsMoney.entity.Activity;

import javax.servlet.http.HttpServletRequest;

public class PostActivityCreateCommand implements Command {

    //ToDo
    // Replace
    private final DaoFactory daoFactory = JdbcDaoFactory.getInstance();
    private final ActivityDao activityDao = daoFactory.createActivityDao(JdbcConnectionPool.getInstance());

    @Override
    public String execute(HttpServletRequest request) {
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        Activity activity = new Activity();
        activity.setName(name);
        activity.setDescription(description);

        activityDao.save(activity);

        return "redirect:/app/activity";
    }
}
