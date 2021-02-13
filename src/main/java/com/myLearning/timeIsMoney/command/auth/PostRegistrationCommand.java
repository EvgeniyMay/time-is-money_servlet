package com.mylearning.timeismoney.command.auth;

import com.mylearning.timeismoney.command.Command;
import com.mylearning.timeismoney.dto.UserDto;
import com.mylearning.timeismoney.service.UserService;
import com.mylearning.timeismoney.util.UserValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

public class PostRegistrationCommand implements Command {

    private final UserService userService;
    public PostRegistrationCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        UserDto userDto = new UserDto();
        userDto.setLogin(login);
        userDto.setPassword(password);

        if(!UserValidator.getErrors(userDto).isEmpty()){
            request.setAttribute("errors", UserValidator.getErrors(userDto));
            return "/WEB-INF/jsp/auth/registration.jsp";
        }

        try {
            userService.create(userDto);
        } catch (Exception e) {
            request.setAttribute("errors", Arrays.asList("Login exists"));
            return "/WEB-INF/jsp/auth/registration.jsp";
        }
        return "redirect:/app";
    }
}
