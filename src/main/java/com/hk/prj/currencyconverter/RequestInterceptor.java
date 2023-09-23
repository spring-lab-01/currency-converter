package com.hk.prj.currencyconverter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class RequestInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        MDC.put("path", request.getMethod());
        MDC.put("path", request.getServletPath());
        MDC.put("startTime", LocalDateTime.now().toString());
        System.out.println("request start -- "+request.getServletPath());
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        MDC.put("endTime", LocalDateTime.now().toString());
        System.out.printf("""
                {
                 "path": "%s",
                 "startTime":"%s",
                 "endTime":"%s"
                }
                """, MDC.get("path"), MDC.get("startTime"), MDC.get("endTime"));
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
