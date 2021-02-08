package com.myLearning.timeIsMoney.command.activity;

import com.myLearning.timeIsMoney.command.Command;

import javax.servlet.http.HttpServletRequest;

public class GetActivityCreateCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {

        return "/WEB-INF/jsp/activity/activityAdd.jsp";
    }
}
