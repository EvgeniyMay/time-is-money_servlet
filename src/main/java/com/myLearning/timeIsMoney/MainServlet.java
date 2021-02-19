package com.mylearning.timeismoney;

import com.mylearning.timeismoney.command.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class MainServlet extends HttpServlet {

    private final static Logger logger = Logger.getLogger(MainServlet.class.getName());

    private CommandController commandController;

    @Override
    public void init() throws ServletException {
        commandController = CommandController.getInstance();
        getServletContext().setAttribute("authedUsers", new ArrayList<Integer>());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page = commandController.getCommand(request).execute(request);
        logger.info(page);

        if(page.contains("redirect:")) {
            response.sendRedirect(page.replace("redirect:", ""));
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
