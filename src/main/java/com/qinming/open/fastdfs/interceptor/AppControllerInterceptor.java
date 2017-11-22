package com.qinming.open.fastdfs.interceptor;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * Created by QinMing on 2017/10/26.
 */
public class AppControllerInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object o) throws Exception {
        req.setAttribute("startTime", System.currentTimeMillis());

        JSONObject params = new JSONObject();
        params.put("tokenId", req.getHeader("tokenId"));

        Enumeration<String> names = req.getParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            params.put(name, req.getParameter(name));
        }
        logger.info("++Request, {}, {}, {}", req.getRemoteAddr(), req.getRequestURI(), params.toString());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest req, HttpServletResponse res, Object o, ModelAndView m) throws Exception {
        Long startTime = (Long) req.getAttribute("startTime");
        Long executeTime = System.currentTimeMillis() - startTime;
        logger.info("--Request, {}, {}, {} millis", req.getRemoteAddr(), req.getRequestURI(), executeTime);
    }

    @Override
    public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object o, Exception e) throws Exception {
    }
}
