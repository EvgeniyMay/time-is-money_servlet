package com.mylearning.timeismoney.command.activity;

import com.mylearning.timeismoney.command.Command;

import javax.servlet.http.HttpServletRequest;

public class GetArchiveActivityCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");

        request.setAttribute("id", id);
        request.setAttribute("name", name);

        return "/WEB-INF/jsp/activity/activityArchive.jsp";
    }
}
