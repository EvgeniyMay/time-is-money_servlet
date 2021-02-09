package com.myLearning.timeIsMoney.command;

import com.myLearning.timeIsMoney.command.activity.*;
import com.myLearning.timeIsMoney.dao.ActivityDao;
import com.myLearning.timeIsMoney.dao.ConnectionPool;
import com.myLearning.timeIsMoney.dao.DaoFactory;
import com.myLearning.timeIsMoney.dao.impl.JdbcConnectionPool;
import com.myLearning.timeIsMoney.dao.impl.JdbcDaoFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CommandController {

    private Map<String, Command> getCommands;
    private Map<String, Command> postCommands;

    private static CommandController commandController;

    public static CommandController getInstance() {
        if(commandController == null) {
            synchronized (CommandController.class) {
                if(commandController == null) {
                    commandController = new CommandController();
                }
            }
        }
        return commandController;
    }

    private CommandController () {
        DaoFactory daoFactory = JdbcDaoFactory.getInstance();
        ActivityDao activityDao = daoFactory.createActivityDao();

        getCommands = new HashMap<>();
        getCommands.put("/activity", new GetActivityCommand(activityDao));
        getCommands.put("/activity/add", new GetActivityCreateCommand());
        getCommands.put("/activity/delete", new GetActivityDeleteCommand());
        getCommands.put("/activity/edit", new GetActivityEditCommand(activityDao));

        postCommands = new HashMap<>();
        postCommands.put("/activity/add", new PostActivityCreateCommand(activityDao));
        postCommands.put("/activity/delete", new PostActivityDeleteCommand(activityDao));
        postCommands.put("/activity/edit", new PostActivityEditCommand(activityDao));
    }

    public Command getCommand(HttpServletRequest request) {
        String path = request.getRequestURI().replace("/app", "");

        if("GET".equals(request.getMethod())) {
            return getCommands.getOrDefault(path, r -> "/main.jsp");
        }
        return postCommands.getOrDefault(path, r -> "/main.jsp");
    }
}
