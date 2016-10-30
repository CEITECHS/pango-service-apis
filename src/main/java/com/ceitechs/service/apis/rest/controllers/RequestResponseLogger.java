package com.ceitechs.service.apis.rest.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author iddymagohe on 10/29/16.
 * @since 1.0
 */
@Component
public class RequestResponseLogger extends HandlerInterceptorAdapter {

    Logger logger= LoggerFactory.getLogger(RequestResponseLogger.class);

    String confidentialMarkerText = "TRACKING";

    Marker confidentialMarker = MarkerFactory.getMarker(confidentialMarkerText);

    private static final String REQUEST_START_TIME = "startTime";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long startTime = System.currentTimeMillis();
        request.setAttribute(REQUEST_START_TIME, startTime);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long startTime = (Long) request.getAttribute(REQUEST_START_TIME);
        long duration = System.currentTimeMillis() - startTime;

//        String uri = request.getScheme() + "://" +   // "http" + "://
//                request.getServerName() +       // "myhost"
//                ":" +                           // ":"
//                request.getServerPort() +       // "8080"
//                request.getRequestURI() +       // "/people"
//                "?" +                           // "?"
//                request.getQueryString();       // "lastname=Fox&age=30"

        StringBuilder requestToLog = new StringBuilder();

        requestToLog.append(" DateAndTime :");
        requestToLog.append(response.getHeader("Date"));

        requestToLog.append(" StartTime : ");
        requestToLog.append(startTime);

        requestToLog.append(" Duration :" );
        requestToLog.append(duration);

        requestToLog.append(" URI :" );
        requestToLog.append(request.getScheme());
        requestToLog.append("://");
        requestToLog.append(request.getServerName());
        requestToLog.append(":");
        requestToLog.append(request.getServerPort());
        requestToLog.append(request.getRequestURI());
        requestToLog.append(request.getQueryString() != null ? "?" : "");
        requestToLog.append(request.getQueryString() != null ? request.getQueryString() : "");

        requestToLog.append(" MediaType :");
        requestToLog.append(request.getContentType());

        requestToLog.append(" RequestFromHost :");
        requestToLog.append(request.getRemoteHost());

        requestToLog.append(" RequestFromIP :");
        requestToLog.append(request.getRemoteAddr());

        requestToLog.append(" RequestTo :" );
        requestToLog.append(request.getLocalAddr());

        requestToLog.append(" ResponseStatus :");
        requestToLog.append(+ response.getStatus());
        logger.info(confidentialMarker, requestToLog.toString());
    }
}
