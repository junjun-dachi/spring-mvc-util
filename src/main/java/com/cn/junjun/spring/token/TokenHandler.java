package com.cn.junjun.spring.token;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Component;

/**
 * This is a helper class used for HTTP request token generation and save in the
 * cache.
 * 
 * @author junjun
 * @since 28 NOV 2014
 * 
 */

@Component
public class TokenHandler {

    @Autowired
    private org.springframework.cache.CacheManager cacheManager;

    public String generate() {
        Cache cache = cacheManager.getCache("tokens");
        String token = UUID.randomUUID().toString();
        cache.put(token, token);
        return token;

    }

}
