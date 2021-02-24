package com.mylearning.timeismoney.command;

import javax.servlet.http.HttpServletRequest;

/**
 * Ensures execute method
 */
public interface Command {
    /**
     * @param request http request
     * @return name of page or redirect path
     */
    String execute(HttpServletRequest request);

}
