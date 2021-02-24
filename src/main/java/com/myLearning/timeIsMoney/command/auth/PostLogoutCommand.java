package com.mylearning.timeismoney.command.auth;

import com.mylearning.timeismoney.command.Command;
import com.mylearning.timeismoney.entity.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * logout command
 */
public class PostLogoutCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("authUser");

        session.invalidate();

        ServletContext servletContext = request.getServletContext();
        List<Integer> authedUsers = (List<Integer>)servletContext.getAttribute("authedUsers");

        authedUsers.remove(new Integer(user.getId()));

        return "redirect:/";
    }
}
