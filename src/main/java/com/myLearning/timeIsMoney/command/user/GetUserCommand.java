package com.myLearning.timeIsMoney.command.user;

import com.myLearning.timeIsMoney.command.Command;
import com.myLearning.timeIsMoney.dao.UserDao;

import javax.servlet.http.HttpServletRequest;

public class GetUserCommand implements Command {

    private final UserDao userDao;

    public GetUserCommand(UserDao userDao) {
        this.userDao =userDao;
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("users", userDao.findAll());

        return "/WEB-INF/jsp/user/userAll.jsp";
    }
}
