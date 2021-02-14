package com.mylearning.timeismoney.command;

import com.mylearning.timeismoney.command.activity.*;
import com.mylearning.timeismoney.command.auth.*;
import com.mylearning.timeismoney.command.mission.GetCreateMissionCommand;
import com.mylearning.timeismoney.command.mission.GetMissionCommand;
import com.mylearning.timeismoney.command.mission.PostCreateMissionCommand;
import com.mylearning.timeismoney.command.user.GetUserCommand;
import com.mylearning.timeismoney.command.user.GetUserProfile;
import com.mylearning.timeismoney.dao.DaoFactory;
import com.mylearning.timeismoney.dao.impl.JdbcDaoFactory;
import com.mylearning.timeismoney.service.ActivityService;
import com.mylearning.timeismoney.service.MissionService;
import com.mylearning.timeismoney.service.UserService;

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
        MissionService missionService = new MissionService(daoFactory);

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
        getCommands.put("/registration", new GetRegistrationCommand());
        getCommands.put("/login", new GetLoginCommand());
        getCommands.put("/logout", new GetLogoutCommand());

        postCommands.put("/registration", new PostRegistrationCommand(userService));
        postCommands.put("/login", new PostLoginCommand(userService));
        postCommands.put("/logout", new PostLogoutCommand());
//        User
        getCommands.put("/user", new GetUserCommand(userService));
        getCommands.put("/profile", new GetUserProfile());
//        Mission
        getCommands.put("/mission", new GetMissionCommand(missionService));
        getCommands.put("/mission/add", new GetCreateMissionCommand(missionService));

        postCommands.put("/mission/add", new PostCreateMissionCommand(missionService));
    }

    public Command getCommand(HttpServletRequest request) {
        String path = request.getRequestURI().replace("/app", "");

        if("GET".equals(request.getMethod())) {
            return getCommands.getOrDefault(path, r -> "/main.jsp");
        }
        return postCommands.getOrDefault(path, r -> "/main.jsp");
    }
}
