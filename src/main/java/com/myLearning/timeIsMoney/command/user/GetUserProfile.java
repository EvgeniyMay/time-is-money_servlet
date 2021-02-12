package com.myLearning.timeIsMoney.command.user;

import com.myLearning.timeIsMoney.command.Command;
import com.myLearning.timeIsMoney.entity.User;

import javax.servlet.http.HttpServletRequest;

public class GetUserProfile implements Command {

    @Override
    public String execute(HttpServletRequest request) {

        return "/WEB-INF/jsp/user/userProfile.jsp";
    }
}