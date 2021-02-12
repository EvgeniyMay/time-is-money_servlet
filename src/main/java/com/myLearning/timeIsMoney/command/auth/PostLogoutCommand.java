package com.myLearning.timeIsMoney.command.auth;

import com.myLearning.timeIsMoney.command.Command;
import com.myLearning.timeIsMoney.entity.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class PostLogoutCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("auth");

        session.invalidate();

        ServletContext servletContext = request.getServletContext();
        List<Integer> authedUsers = (List<Integer>)servletContext.getAttribute("authedUsers");

        authedUsers.remove(new Integer(user.getId()));

        return "redirect:/";
    }
}
