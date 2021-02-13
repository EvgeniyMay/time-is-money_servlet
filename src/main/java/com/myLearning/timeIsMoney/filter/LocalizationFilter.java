package com.myLearning.timeIsMoney.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "LocalizationFilter", urlPatterns = { "/*" })
public class LocalizationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {

        if (request.getParameter("lang") != null) {
            ((HttpServletRequest)request).getSession().setAttribute("lang", request.getParameter("lang"));
        }

        filterChain.doFilter(request, response);

    }
}
