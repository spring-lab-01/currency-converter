package com.hk.prj.currencyconverter.interceptor;

import com.hk.prj.currencyconverter.aws.S3Handler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalDateTime;


public class RequestInterceptor implements HandlerInterceptor {

    String bucket = "hk-log-bucket-00";
    String key = "currency-converter-app-log";

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
        String log =
                """
                {
                 "path": "%s",
                 "startTime":"%s",
                 "endTime":"%s"
                }
                """.formatted(MDC.get("path"), MDC.get("startTime"), MDC.get("endTime"));
        S3Handler.putObject(bucket, key+"_"+System.currentTimeMillis(), log);
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
        MDC.clear();
    }
}
