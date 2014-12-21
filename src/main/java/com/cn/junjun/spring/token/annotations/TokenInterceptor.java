package com.cn.junjun.spring.token.annotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * This class is used as an interceptor for token validation. If token is not
 * valid set error to http request.
 * 
 * @author junjun
 *
 */
@Component("tokenInterceptor")
public class TokenInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = Logger.getLogger(TokenInterceptor.class);

    @Autowired
    private org.springframework.cache.CacheManager cacheManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        boolean valid = true;

        HandlerMethod method = (HandlerMethod) handler;

        CheckToken annotation = method.getMethodAnnotation(CheckToken.class);
        if (annotation != null) {
            String token = request.getParameter("token");
            logger.info("token in the request <" + token + ">.");
            Cache cache = cacheManager.getCache("tokens");
            if (StringUtils.isEmpty(token)) {
                valid = false;
                logger.info("token not found in the request.");
            } else if (!StringUtils.isEmpty(token) && cache.get(token) != null && !token.equals(cache.get(token).get())) {
                valid = false;
                logger.info("token in the cache <" + cache.get(token) == null ? "" : cache.get(token).get() + ">.");
            } else {
                if (annotation.remove()) {
                    cache.evict(token);
                }
            }

            if (!valid) {
                logger.info("token is not valid , set error to request");
                request.setAttribute("error", "invalid token");
                // response.sendRedirect(request.getContextPath() +
                // "/error.htm");
            }
        }

        return super.preHandle(request, response, handler);
    }

}
