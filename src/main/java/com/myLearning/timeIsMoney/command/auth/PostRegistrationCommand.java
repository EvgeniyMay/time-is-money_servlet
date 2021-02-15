package com.mylearning.timeismoney.command.auth;

import com.mylearning.timeismoney.command.Command;
import com.mylearning.timeismoney.entity.User;
import com.mylearning.timeismoney.service.UserService;
import com.mylearning.timeismoney.util.UserValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;

public class PostRegistrationCommand implements Command {

    private final UserService userService;
    public PostRegistrationCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        User user = new User.Builder()
                .login(request.getParameter("login"))
                .password(request.getParameter("password"))
                .build();

        if(!UserValidator.getErrors(user).isEmpty()){
            request.setAttribute("errors", UserValidator.getErrors(user));
            return "/WEB-INF/jsp/auth/registration.jsp";
        }

        try {
            userService.create(user);
        } catch (Exception e) {
            request.setAttribute("errors", Collections.singletonList("Login exists"));
            return "/WEB-INF/jsp/auth/registration.jsp";
        }

        return "redirect:/app/login";
    }
}
