package com.myLearning.timeIsMoney.filter;

import com.myLearning.timeIsMoney.entity.User;
import com.myLearning.timeIsMoney.entity.enums.Role;
import com.myLearning.timeIsMoney.exception.AuthorizationException;
import com.myLearning.timeIsMoney.exception.PageNotFoundException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        User user = (User) httpRequest.getSession().getAttribute("authUser");
        String path = httpRequest.getRequestURI().replace("/app", "");

        if(Objects.isNull(user)) {
            if(!Role.GUEST.getAuthorities().contains(path)) {
                throw new AuthorizationException();
            } else {
                filterChain.doFilter(request, response);
            }
            return;
        }

        if(!user.getRole().getAuthorities().contains(path)) {
            throw new PageNotFoundException();
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
