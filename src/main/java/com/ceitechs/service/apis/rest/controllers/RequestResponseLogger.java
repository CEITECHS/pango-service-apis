package com.ceitechs.service.apis.rest.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author iddymagohe on 10/29/16.
 * @since 1.0
 */
public class RequestResponseLogger extends HandlerInterceptorAdapter {

    Logger logger= LoggerFactory.getLogger(RequestResponseLogger.class);

    private static final String REQUEST_START_TIME = "startTime";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long startTime = System.currentTimeMillis();
        request.setAttribute(REQUEST_START_TIME, startTime);
        return true;
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long startTime = (Long) request.getAttribute(REQUEST_START_TIME);
        long duration = System.currentTimeMillis() - startTime;
        StringBuilder requestToLog = new StringBuilder();
        requestToLog.append(" Time :" + response.getHeader("Date"));
        requestToLog.append(" StartTime : " + startTime);
        requestToLog.append(" Duration :" + duration);
        requestToLog.append(" Request :  " + request.getContextPath() + " " + request.getPathInfo() + " " + request.getContentType());
        requestToLog.append(" RequestFromHost :  " + request.getRemoteHost());
        requestToLog.append(" RequestFromIP:  " + request.getRemoteAddr());
        requestToLog.append(" RequestTo  :  " + request.getLocalAddr());
        requestToLog.append(" ResponseStatus : " + +response.getStatus());
        logger.info(requestToLog.toString());
    }
}
