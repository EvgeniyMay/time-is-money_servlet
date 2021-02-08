package com.myLearning.timeIsMoney.command.activity;

import com.myLearning.timeIsMoney.command.Command;
import com.myLearning.timeIsMoney.dao.JdbcActivityDao;

import javax.servlet.http.HttpServletRequest;

public class PostActivityDeleteCommand implements Command {

    //ToDo
    // Replace
    private final JdbcActivityDao jdbcActivityDao = new JdbcActivityDao();

    @Override
    public String execute(HttpServletRequest request) {
        String name = request.getParameter("name");

        jdbcActivityDao.deleteByName(name);

        return "redirect:/app/activity";
    }
}
