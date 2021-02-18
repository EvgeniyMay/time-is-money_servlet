package com.mylearning.timeismoney.filter;

import com.mylearning.timeismoney.entity.User;
import com.mylearning.timeismoney.entity.enums.Role;
import com.mylearning.timeismoney.exception.AuthorizationException;
import com.mylearning.timeismoney.exception.PageNotFoundException;

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
        //ToDo
        // !!! Evgeniy Borisov !!!

        if(!user.getRole().getAuthorities().contains(path)) {
            throw new PageNotFoundException();
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
