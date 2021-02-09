package com.myLearning.timeIsMoney.command.activity;

import com.myLearning.timeIsMoney.command.Command;
import com.myLearning.timeIsMoney.dao.ActivityDao;
import com.myLearning.timeIsMoney.entity.Activity;

import javax.servlet.http.HttpServletRequest;

public class PostActivityCreateCommand implements Command {

    private final ActivityDao activityDao;

    public PostActivityCreateCommand(ActivityDao activityDao) {
        this.activityDao = activityDao;
    }

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
