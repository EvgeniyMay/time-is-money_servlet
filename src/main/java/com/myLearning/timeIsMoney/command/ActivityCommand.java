package com.myLearning.timeIsMoney.command;

import javax.servlet.http.HttpServletRequest;

public class ActivityCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/jsp/activity/activityAll.jsp";
    }
}
