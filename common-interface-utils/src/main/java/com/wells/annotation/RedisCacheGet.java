package com.wells.annotation;

import java.lang.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zb on 2019/3/25
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface RedisCacheGet {
    /**
     * key值
     *
     * @return
     */
     String key();
    /**
     * 冲当键
     *
     * @return
     */

     String field() default "";

    /**
     * value值
     *
     * @return
     */
     String value()default "";

    /**给key用的
     * 缓存过期时间默认为不过期，过期时间手动去设定，单位为 S
     * 0:不限制保存时长
     *
     * @return
     */
     int expire() default -1;

    /**
     * 缓存过期时间默认为不过期，过期时间手动去设定，单位为 S
     * 0:不限制保存时长
     *
     * @return
     */
     boolean cacheEnable() default true;

    /**给field用的
     * 缓存过期时间默认为不过期，过期时间手动去设定，单位为 S
     * 0:不限制保存时长
     *
     * @return
     */
    boolean fieldExpire()default false;
}
