package com.myLearning.timeIsMoney.command;

import com.myLearning.timeIsMoney.command.activity.*;
import com.myLearning.timeIsMoney.command.auth.*;
import com.myLearning.timeIsMoney.command.user.GetUserCommand;
import com.myLearning.timeIsMoney.command.user.GetUserProfile;
import com.myLearning.timeIsMoney.dao.DaoFactory;
import com.myLearning.timeIsMoney.dao.impl.JdbcDaoFactory;
import com.myLearning.timeIsMoney.service.ActivityService;
import com.myLearning.timeIsMoney.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CommandController {

    private Map<String, Command> getCommands;
    private Map<String, Command> postCommands;

    private static CommandController commandController;

    public static CommandController getInstance() {
        if(commandController == null) {
            commandController = new CommandController();
        }
        return commandController;
    }

    private CommandController () {
        DaoFactory daoFactory = JdbcDaoFactory.getInstance();
        ActivityService activityService = new ActivityService(daoFactory);
        UserService userService = new UserService(daoFactory);

        getCommands = new HashMap<>();
        postCommands = new HashMap<>();

//        Activity
        getCommands.put("/activity", new GetActivityCommand(activityService));
        getCommands.put("/activity/add", new GetActivityCreateCommand());
        getCommands.put("/activity/delete", new GetActivityDeleteCommand());
        getCommands.put("/activity/edit", new GetActivityEditCommand(activityService));

        postCommands.put("/activity/add", new PostActivityCreateCommand(activityService));
        postCommands.put("/activity/delete", new PostActivityDeleteCommand(activityService));
        postCommands.put("/activity/edit", new PostActivityEditCommand(activityService));
//        Auth
        getCommands.put("/user", new GetUserCommand(userService));
        getCommands.put("/registration", new GetRegistrationCommand());
        getCommands.put("/login", new GetLoginCommand());
        getCommands.put("/logout", new GetLogoutCommand());

        postCommands.put("/registration", new PostRegistrationCommand(userService));
        postCommands.put("/login", new PostLoginCommand(userService));
        postCommands.put("/logout", new PostLogoutCommand());

//        User
        getCommands.put("/profile", new GetUserProfile());
    }

    public Command getCommand(HttpServletRequest request) {
        String path = request.getRequestURI().replace("/app", "");

        if("GET".equals(request.getMethod())) {
            return getCommands.getOrDefault(path, r -> "/main.jsp");
        }
        return postCommands.getOrDefault(path, r -> "/main.jsp");
    }
}
