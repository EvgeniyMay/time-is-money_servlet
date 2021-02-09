package com.myLearning.timeIsMoney.command.activity;

import com.myLearning.timeIsMoney.command.Command;
import com.myLearning.timeIsMoney.dao.ActivityDao;
import com.myLearning.timeIsMoney.entity.Activity;

import javax.servlet.http.HttpServletRequest;

public class PostActivityEditCommand implements Command {

    private final ActivityDao activityDao;

    public PostActivityEditCommand(ActivityDao activityDao) {
        this.activityDao = activityDao;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        Activity activity = new Activity();
        activity.setId(id);
        activity.setName(name);
        activity.setDescription(description);

        activityDao.update(activity);

        return "redirect:/app/activity";
    }
}
