package com.myLearning.timeIsMoney.command.auth;

import com.myLearning.timeIsMoney.command.Command;
import com.myLearning.timeIsMoney.dao.UserDao;
import com.myLearning.timeIsMoney.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class PostLoginCommand implements Command {

    private final UserDao userDao;

    public PostLoginCommand(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if(login == null || password == null) {
            request.setAttribute("error", "Please, fill all fields");
            return "/WEB-INF/jsp/auth/login.jsp";
        }

        User user;
        try {
             user = userDao.findByLogin(login);
        } catch (Exception e) {
            request.setAttribute("error", "Such user does not exist");
            return "/WEB-INF/jsp/auth/login.jsp";
        }

        if(!user.getPassword().equals(password)) {
            request.setAttribute("error", "Wrong login or password");
            return "/WEB-INF/jsp/auth/login.jsp";
        }

        HttpSession session = request.getSession();
        session.setAttribute("auth", user);

        return "redirect:/";
    }
}
