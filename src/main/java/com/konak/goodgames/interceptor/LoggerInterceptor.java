package com.konak.goodgames.interceptor;

import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@Slf4j
@Component
public class LoggerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(final HttpServletRequest request, @NonNull final HttpServletResponse response, @NonNull final Object handler) {
        log.info("[preHandle][" + request.getMethod() + "]" + request.getRequestURI() + getParameters(request));
        return true;
    }

    @Override
    public void postHandle(@NonNull final HttpServletRequest request,@NonNull final HttpServletResponse response,@NonNull final Object handler, final ModelAndView modelAndView) {
        log.info("[postHandle][" + response.getStatus() + "]");
    }

    @Override
    public void afterCompletion(@org.springframework.lang.NonNull final HttpServletRequest request, @org.springframework.lang.NonNull final HttpServletResponse response, @NonNull final Object handler, final Exception ex) {
        if (ex != null) {
            ex.printStackTrace();
            log.info("[afterCompletion][exception: " + ex + "]");
        }
    }

    private String getParameters(final HttpServletRequest request) {
        final StringBuilder posted = new StringBuilder();
        final Enumeration<?> e = request.getParameterNames();
        if (e != null)
            posted.append("?");
        while (e != null && e.hasMoreElements()) {
            if (posted.length() > 1)
                posted.append("&");
            final String curr = (String) e.nextElement();
            posted.append(curr)
                    .append("=");
            if (curr.contains("password") || curr.contains("answer") || curr.contains("pwd")) {
                posted.append("*****");
            } else {
                posted.append(request.getParameter(curr));
            }
        }

        final String ip = request.getHeader("X-FORWARDED-FOR");
        final String ipAddr = (ip == null) ? getRemoteAddr(request) : ip;
        if (!Strings.isNullOrEmpty(ipAddr))
            posted.append("&_psip=").append(ipAddr);
        return posted.toString();
    }

    private String getRemoteAddr(final HttpServletRequest request) {
        final String ipFromHeader = request.getHeader("X-FORWARDED-FOR");
        if (ipFromHeader != null && ipFromHeader.length() > 0) {
            log.debug("ip from proxy - X-FORWARDED-FOR : " + ipFromHeader);
            return ipFromHeader;
        }
        return request.getRemoteAddr();
    }
}
