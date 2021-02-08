package com.myLearning.timeIsMoney;

import com.myLearning.timeIsMoney.command.*;
import com.myLearning.timeIsMoney.command.activity.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainServlet extends HttpServlet {

    private Map<String, Command> getCommands;
    private Map<String, Command> postCommands;

    @Override
    public void init() throws ServletException {
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response, getCommands);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response, postCommands);
    }

    private void process(HttpServletRequest request, HttpServletResponse response, Map<String, Command> commands)
            throws ServletException, IOException {
        String path = request.getRequestURI()
                .replace("/app", "");
        //ToDo
        // Log
        System.out.println(path);

        Command command = commands.getOrDefault(path, p -> "/main.jsp");
        String page = command.execute(request);

        if(page.contains("redirect:")) {
            response.sendRedirect(page.replace("redirect:", ""));
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }

    }
}
