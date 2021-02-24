package com.mylearning.timeismoney.filter;

import com.mylearning.timeismoney.entity.User;
import com.mylearning.timeismoney.entity.enums.Role;
import com.mylearning.timeismoney.exception.AuthorizationException;
import com.mylearning.timeismoney.exception.PageNotFoundException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * control access to pages for different users
 */
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        // Current user
        User user = (User) httpRequest.getSession().getAttribute("authUser");
        // Current path
        String path = httpRequest.getRequestURI().replace("/app", "");

        /**
         * @throws AuthorizationException when user has not access to current page
         */
        if(Objects.isNull(user)) {
            if(!Role.GUEST.getAuthorities().contains(path)) {
                throw new AuthorizationException();
            } else {
                filterChain.doFilter(request, response);
            }
            return;
        }

        /**
         * @throws PageNotFoundException when user has not access to current page
         */
        if(!user.getRole().getAuthorities().contains(path)) {
            throw new PageNotFoundException();
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
