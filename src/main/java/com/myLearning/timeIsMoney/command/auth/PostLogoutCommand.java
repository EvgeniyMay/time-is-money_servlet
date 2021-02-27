package com.mylearning.timeismoney.command.auth;

import com.mylearning.timeismoney.command.Command;
import com.mylearning.timeismoney.entity.User;
import com.mylearning.timeismoney.service.AuthService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * logout command
 */
public class PostLogoutCommand implements Command {

    private final AuthService authService;

    public PostLogoutCommand(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("authUser");

        session.invalidate();

        authService.dropUser(user);

        return "redirect:/";
    }
}
