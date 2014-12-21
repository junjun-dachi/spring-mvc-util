package com.cn.junjun.spring.token.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This class is used as annotation for http requests that needed to have a
 * token.
 * 
 * parameter : remove if it is true then remove from cache.
 * 
 * @author junjun
 *
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckToken {

    boolean remove() default true;

}
