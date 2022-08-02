package com.techbooker.auth.config.intercepter;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;

import static com.techbooker.sm.util.CommonUtil.traceIDGenerator;

@Component
@Slf4j
public class UserRequestInterceptor implements HandlerInterceptor {
    public static final String TRACE_ID = "trace-id";
    private static final String START_TIME = "startTime";

    @Override
    public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) {
        final String traceId = (request.getHeader(TRACE_ID) == null ?
                traceIDGenerator("USE") :
                request.getHeader(TRACE_ID));

        MDC.put(TRACE_ID, traceId);
        request.setAttribute(START_TIME, Instant.now().toEpochMilli());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler, Exception ex) {
        long startTime = (Long) request.getAttribute(START_TIME);
        log.info("{}|{}ms", request.getRequestURI(), (Instant.now().toEpochMilli() - startTime));
        MDC.remove(TRACE_ID);
    }
}
