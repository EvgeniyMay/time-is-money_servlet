package com.myLearning.timeIsMoney.command.activity;

import com.myLearning.timeIsMoney.command.Command;

import javax.servlet.http.HttpServletRequest;

public class GetActivityDeleteCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");

        request.setAttribute("id", id);
        request.setAttribute("name", name);

        return "/WEB-INF/jsp/activity/activityDelete.jsp";
    }
}
