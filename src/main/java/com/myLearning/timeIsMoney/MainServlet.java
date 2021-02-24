package com.mylearning.timeismoney;

import com.mylearning.timeismoney.command.*;
import com.mylearning.timeismoney.dao.impl.JdbcDaoFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Main servlet handles all request
 * and executes command from CommandController by request
 *
 * @see com.mylearning.timeismoney.command.CommandController
 */
public class MainServlet extends HttpServlet {
    /**
     * Logger for logging important information
     */
    private final static Logger logger = LogManager.getLogger();
    /**
     * From this object servlet takes commands to execute
     */
    private CommandController commandController;
    /**
     * Initialization of CommandController
     * and List of authorized users
     */
    @Override
    public void init() throws ServletException {
        commandController = CommandController.getInstance(
                JdbcDaoFactory.getInstance());

        getServletContext().setAttribute("authedUsers", new ArrayList<Integer>());
        logger.info("MainServlet created");
    }
    /**
     * Servlet handles request
     * and redirects executing to process function
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }
    /**
     * Request handling
     * Servlet executes command by request from CommandController
     */
    private void process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page = commandController.getCommand(request).execute(request);
        logger.info("Request to {}",page);

        if(page.contains("redirect:")) {
            response.sendRedirect(page.replace("redirect:", ""));
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
