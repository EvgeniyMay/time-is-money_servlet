package com.myLearning.timeIsMoney.command.auth;

import com.myLearning.timeIsMoney.command.Command;
import com.myLearning.timeIsMoney.entity.User;
import com.myLearning.timeIsMoney.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class PostLoginCommand implements Command {

    private final UserService userService;

    public PostLoginCommand(UserService userService) {
        this.userService = userService;
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
             user = userService.findByLogin(login);
        } catch (Exception e) {
            request.setAttribute("error", "Such user does not exist");
            return "/WEB-INF/jsp/auth/login.jsp";
        }

        if(!user.getPassword().equals(password)) {
            request.setAttribute("error", "Wrong login or password");
            return "/WEB-INF/jsp/auth/login.jsp";
        }

        ServletContext servletContext = request.getServletContext();
        List<Integer> authedUsers = (List<Integer>)servletContext.getAttribute("authedUsers");
        if(authedUsers.contains(user.getId())) {
            request.setAttribute("error", "Such user authorized");
            return "/WEB-INF/jsp/auth/login.jsp";
        }

        authedUsers.add(user.getId());

        HttpSession session = request.getSession();
        session.setAttribute("authUser", user);

        return "redirect:/";
    }
}
