package com.myLearning.timeIsMoney.command;

import com.myLearning.timeIsMoney.command.activity.*;

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
        getCommands = new HashMap<>();
        getCommands.put("/activity", new GetActivityCommand());
        getCommands.put("/activity/add", new GetActivityCreateCommand());
        getCommands.put("/activity/delete", new GetActivityDeleteCommand());
        getCommands.put("/activity/edit", new GetActivityEditCommand());

        postCommands = new HashMap<>();
        postCommands.put("/activity/add", new PostActivityCreateCommand());
        postCommands.put("/activity/delete", new PostActivityDeleteCommand());
        postCommands.put("/activity/edit", new PostActivityEditCommand());
    }

    public Command getCommand(HttpServletRequest request) {
        String path = request.getRequestURI().replace("/app", "");

        if("GET".equals(request.getMethod())) {
            return getCommands.getOrDefault(path, r -> "/main.jsp");
        }
        return postCommands.getOrDefault(path, r -> "/main.jsp");
    }
}
