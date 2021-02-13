package com.mylearning.timeismoney.filter;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        filterChain.doFilter(request, response);
    }
}
