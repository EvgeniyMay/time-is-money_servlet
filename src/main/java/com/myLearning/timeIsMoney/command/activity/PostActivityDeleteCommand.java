package com.myLearning.timeIsMoney.command.activity;

import com.myLearning.timeIsMoney.command.Command;
import com.myLearning.timeIsMoney.dao.ActivityDao;

import javax.servlet.http.HttpServletRequest;

public class PostActivityDeleteCommand implements Command {

    private final ActivityDao activityDao;

    public PostActivityDeleteCommand(ActivityDao activityDao) {
        this.activityDao = activityDao;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));

        activityDao.deleteById(id);

        return "redirect:/app/activity";
    }
}
