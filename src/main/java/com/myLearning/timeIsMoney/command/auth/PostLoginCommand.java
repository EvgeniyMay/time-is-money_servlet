package com.mylearning.timeismoney.command.auth;

import com.mylearning.timeismoney.command.Command;
import com.mylearning.timeismoney.entity.User;
import com.mylearning.timeismoney.service.AuthService;
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
    private final AuthService authService;

    public PostLoginCommand(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        User user;
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if(String.valueOf(login).isEmpty() || String.valueOf(password).isEmpty()) {
            request.setAttribute("errorProperty", "input.error.empty.field");
            return "/WEB-INF/jsp/auth/login.jsp";
        }

        try {
             user = userService.findByLoginProxy(login);
        } catch (Exception e) {
            request.setAttribute("errorProperty", "login.error.wrong.user");
            return "/WEB-INF/jsp/auth/login.jsp";
        }

        if(!user.getPassword().equals(DigestUtils.sha256Hex(password))) {
            request.setAttribute("errorProperty", "login.error.wrong.user");
            return "/WEB-INF/jsp/auth/login.jsp";
        }

        if(!authService.authUser(user)) {
            request.setAttribute("errorProperty", "login.error.auth.user");
            return "/WEB-INF/jsp/auth/login.jsp";
        }

        HttpSession session = request.getSession();
        session.setAttribute("authUser", user);

        return "redirect:/app/profile";
    }
}
