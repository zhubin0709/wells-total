package com.wells.annotation;

import java.lang.annotation.*;

/**
 * Created by zb on 2019/3/25
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface RedisCacheClean {
    /**
     * 保存数据库
     *
     * @return
     */
    public int DBIndex();

    /**
     * key值
     *
     * @return
     */
    public  String[] key();

    /**
     * 是否批量
     *
     * @return
     */
    public boolean isBatch() default false;
}
