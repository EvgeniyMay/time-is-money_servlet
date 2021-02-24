package com.mylearning.timeismoney.command.auth;

import com.mylearning.timeismoney.command.Command;
import com.mylearning.timeismoney.entity.User;
import com.mylearning.timeismoney.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * login command
 */
public class PostLoginCommand implements Command {

    private final UserService userService;

    public PostLoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        User user;
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if(String.valueOf(login).isEmpty() || String.valueOf(password).isEmpty()) {
            request.setAttribute("error", "Please, fill all fields");
            return "/WEB-INF/jsp/auth/login.jsp";
        }

        try {
             user = userService.findByLoginProxy(login);
        } catch (Exception e) {
            request.setAttribute("error", "Such user does not exist");
            return "/WEB-INF/jsp/auth/login.jsp";
        }

        if(!user.getPassword().equals(DigestUtils.sha256Hex(password))) {
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

        return "redirect:/app/profile";
    }
}
