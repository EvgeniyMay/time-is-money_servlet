package com.myLearning.timeIsMoney.command;

import javax.servlet.http.HttpServletRequest;

public interface Command {

    public String execute(HttpServletRequest request);

}
