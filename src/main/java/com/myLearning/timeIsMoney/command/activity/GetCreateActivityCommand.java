package com.mylearning.timeismoney.command.activity;

import com.mylearning.timeismoney.command.Command;

import javax.servlet.http.HttpServletRequest;

public class GetCreateActivityCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {

        return "/WEB-INF/jsp/activity/activityAdd.jsp";
    }
}
