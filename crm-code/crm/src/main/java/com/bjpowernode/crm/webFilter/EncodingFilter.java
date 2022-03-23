package com.bjpowernode.crm.webFilter;

import jakarta.servlet.*;

import java.io.IOException;

public class EncodingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        res.setContentType("text/html;charset=UTF-8");
        chain.doFilter(req,res);
    }
}
