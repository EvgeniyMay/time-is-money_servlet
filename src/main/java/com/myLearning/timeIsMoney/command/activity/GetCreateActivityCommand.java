package com.mylearning.timeismoney.command.activity;

import com.mylearning.timeismoney.command.Command;

import javax.servlet.http.HttpServletRequest;

/**
 * form for activity creation
 */
public class GetCreateActivityCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {

        return "/WEB-INF/jsp/activity/activityAdd.jsp";
    }
}
