package com.mylearning.timeismoney.filter;

import com.mylearning.timeismoney.entity.User;
import com.mylearning.timeismoney.entity.enums.Role;
import com.mylearning.timeismoney.exception.AuthorizationException;
import com.mylearning.timeismoney.exception.PageNotFoundException;
import com.mylearning.timeismoney.service.AuthService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * control access to pages for different users
 */
public class AuthFilter implements Filter {

    private final AuthService authService = AuthService.getInstance();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        // Current user
        User user = (User) httpRequest.getSession().getAttribute("authUser");
        // Current path
        String path = httpRequest.getRequestURI().replace("/app", "");

        authService.checkUserAccess(user, path);

        filterChain.doFilter(request, response);
    }
}
