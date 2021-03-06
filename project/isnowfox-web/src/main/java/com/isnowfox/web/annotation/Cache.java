package com.isnowfox.web.annotation;

import com.isnowfox.web.CacheType;

import java.lang.annotation.*;

/**
 * http缓存机制
 *
 * @author zuoge85
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Cache {
    CacheType type() default CacheType.NO_CACHE;

    String value() default "";
}
