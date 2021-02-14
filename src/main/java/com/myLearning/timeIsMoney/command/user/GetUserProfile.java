package com.mylearning.timeismoney.command.user;

import com.mylearning.timeismoney.command.Command;
import com.mylearning.timeismoney.entity.User;
import com.mylearning.timeismoney.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class GetUserProfile implements Command {

    private final UserService userService;
    public GetUserProfile(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute("authUser");
        request.setAttribute("missions", userService.findById(user.getId()).getMissions());

        return "/WEB-INF/jsp/user/userProfile.jsp";
    }
}
