package com.mylearning.timeismoney.command;

import com.mylearning.timeismoney.command.activity.*;
import com.mylearning.timeismoney.command.auth.*;
import com.mylearning.timeismoney.command.mission.*;
import com.mylearning.timeismoney.command.user.GetUserCommand;
import com.mylearning.timeismoney.command.user.GetUserProfileCommand;
import com.mylearning.timeismoney.dao.DaoFactory;
import com.mylearning.timeismoney.service.ActivityService;
import com.mylearning.timeismoney.service.AuthService;
import com.mylearning.timeismoney.service.MissionService;
import com.mylearning.timeismoney.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Controls path-command binding
 * Return command by path
 */
public class CommandController {
    /**
     * Logger for logging important information
     */
    private final static Logger logger = LogManager.getLogger();
    /**
     * Map of requests with GET method
     */
    private final Map<String, Command> getCommands;
    /**
     * Map of requests with POST method
     */
    private final Map<String, Command> postCommands;

    private static CommandController commandController;

    public static CommandController getInstance(DaoFactory daoFactory) {
        if(commandController == null) {
            commandController = new CommandController(daoFactory);
            logger.info("Command controller created");
        }
        return commandController;
    }

    /**
     * Binding commands to heir paths
     * @param daoFactory
     * daoFactory for services
     */
    private CommandController (DaoFactory daoFactory) {
        ActivityService activityService = new ActivityService(daoFactory);
        UserService userService = new UserService(daoFactory);
        MissionService missionService = new MissionService(daoFactory);

        AuthService authService = AuthService.getInstance();

        getCommands = new HashMap<>();
        postCommands = new HashMap<>();
//        Activity
        getCommands.put("/activity/active", new GetActiveActivityCommand(activityService));
        getCommands.put("/activity/archived", new GetArchivedActivityCommand(activityService));
        getCommands.put("/activity/add", new GetCreateActivityCommand());
        getCommands.put("/activity/archive", new GetArchiveActivityCommand());
        getCommands.put("/activity/edit", new GetEditActivityCommand(activityService));

        postCommands.put("/activity/add", new PostCreateActivityCommand(activityService));
        postCommands.put("/activity/archive", new PostArchiveActivityCommand(activityService));
        postCommands.put("/activity/edit", new PostEditActivityCommand(activityService));
        postCommands.put("/activity/activate", new PostActivateActivityCommand(activityService));
//        Auth
        getCommands.put("/registration", new GetRegistrationCommand());
        getCommands.put("/login", new GetLoginCommand());
        getCommands.put("/logout", new GetLogoutCommand());

        postCommands.put("/registration", new PostRegistrationCommand(userService));
        postCommands.put("/login", new PostLoginCommand(userService, authService));
        postCommands.put("/logout", new PostLogoutCommand(authService));
//        User
        getCommands.put("/user", new GetUserCommand(userService));
        getCommands.put("/profile", new GetUserProfileCommand(userService));
//        Mission
        getCommands.put("/mission/add", new GetCreateMissionCommand(missionService));
        getCommands.put("/mission/offer", new GetOfferMissionCommand(activityService));
        getCommands.put("/mission/offered", new GetOfferedMissionsCommand(missionService));
        getCommands.put("/mission/active", new GetActiveMissionCommand(missionService));
        getCommands.put("/mission/passed", new GetPassedMissionCommand(missionService));
        getCommands.put("/mission/completed", new GetCompletedMissionCommand(missionService));

        postCommands.put("/mission/add", new PostCreateMissionCommand(missionService));
        postCommands.put("/mission/offer", new PostOfferMissionCommand(missionService));
        postCommands.put("/mission/pass", new PostPassMissionCommand(missionService));
        postCommands.put("/mission/cancel", new PostCancelMissionCommand(missionService));
        postCommands.put("/mission/accept", new PostAcceptMissionCommand(missionService));
        postCommands.put("/mission/return", new PostReturnMissionCommand(missionService));
        postCommands.put("/mission/complete", new PostCompleteMissionCommand(missionService));
    }

    /**
     * return Command by request path from request
     * @param request
     * @return command
     * @see com.mylearning.timeismoney.command
     */
    public Command getCommand(HttpServletRequest request) {
        String path = request.getRequestURI().replace("/app", "");

        if("GET".equals(request.getMethod())) {
            return getCommands.getOrDefault(path, r -> "/main.jsp");
        }
        return postCommands.getOrDefault(path, r -> "/main.jsp");
    }
}
