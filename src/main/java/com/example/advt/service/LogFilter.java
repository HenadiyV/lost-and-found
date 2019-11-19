package com.example.advt.service;

import jdk.management.resource.internal.inst.StaticInstrumentation;
import org.springframework.security.web.firewall.RequestRejectedException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *12.11.2019
 */
public class LogFilter implements Filter {




    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)throws IOException, ServletException {
        try {
            chain.doFilter(request, response);
        } catch (Exception e) {
            if( e instanceof RequestRejectedException) {
//                logger.info("Catch RequestRejectedException....");
//                logger.info((HttpServletRequest)request).getRequestURI()); //log the request detail such as its URI
            }
            throw e;
        }
    }
}