package com.myLearning.timeIsMoney.command.auth;

import com.myLearning.timeIsMoney.command.Command;
import com.myLearning.timeIsMoney.dto.UserDto;
import com.myLearning.timeIsMoney.service.UserService;
import com.myLearning.timeIsMoney.util.UserValidator;

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
            request.setAttribute("userForm", new UserDto());
            return "/WEB-INF/jsp/auth/registration.jsp";
        }

        try {
            userService.save(userDto);
        } catch (Exception e) {
            request.setAttribute("errors", Arrays.asList("Login exists"));
            request.setAttribute("userForm", new UserDto());
            return "/WEB-INF/jsp/auth/registration.jsp";
        }
        return "redirect:/app";
    }
}
