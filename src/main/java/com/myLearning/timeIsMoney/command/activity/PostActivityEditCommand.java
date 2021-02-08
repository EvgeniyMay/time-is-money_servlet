package com.myLearning.timeIsMoney.command.activity;

import com.myLearning.timeIsMoney.command.Command;
import com.myLearning.timeIsMoney.dao.JdbcActivityDao;
import com.myLearning.timeIsMoney.entity.Activity;

import javax.servlet.http.HttpServletRequest;

public class PostActivityEditCommand implements Command {

    //ToDo
    // Replace
    private final JdbcActivityDao jdbcActivityDao = new JdbcActivityDao();

    @Override
    public String execute(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        Activity activity = new Activity();
        activity.setId(id);
        activity.setName(name);
        activity.setDescription(description);

        jdbcActivityDao.update(activity);

        return "redirect:/app/activity";
    }
}
