package com.wells.aop;

import com.wells.annotation.RedisCacheGet;
import com.wells.util.AopUtils;
import com.wells.util.RedisUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * Created by zb on 2019/3/27
 */
@Aspect
public class RedisExpirationNoticeAspect {
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 定义缓存逻辑
     */
    @Around("@annotation(com.wells.annotation.RedisCacheGet)")
    public Object RedisCacheGet(ProceedingJoinPoint pjp ) throws NoSuchMethodException{
        //前置部分开始,从redis中查
        Object result=null;
        String fieldKey =null;
        String key=null;
        Method method= AopUtils.getMethod(pjp);
        RedisCacheGet redisCacheGet=method.getAnnotation(com.wells.annotation.RedisCacheGet.class);
        //注解不为空且开启缓存才进入
        if (!(StringUtils.isEmpty(redisCacheGet)||redisCacheGet.cacheEnable()==false||StringUtils.isEmpty(redisCacheGet.key()))){
            //用hash来存值
                    key=AopUtils.parseKey(redisCacheGet.key(),method,pjp.getArgs());
                    //默认方法：如果有key且没有field时,取得则是集合
                    if(StringUtils.isEmpty(redisCacheGet.field())){
                        result=redisUtil.hmget(key);
                    }
                    //如果有key且有field时,取的是一个
                    else{
                        fieldKey =AopUtils.parseKey(redisCacheGet.field(),method,pjp.getArgs());
                        result=redisUtil.hget(key,fieldKey);
                    }
        }
        //redis中有数据立即返回
        if(!StringUtils.isEmpty(result)){
            redisUtil.hset(key,fieldKey,result,redisCacheGet.expire());
            return result;
        }
        //如果redis中为空则进入数据库中查
        try {
            result= pjp.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }

        //后置通知
        //如果数据库中不为空将数据加入到缓存中
        if(!StringUtils.isEmpty(result)){
            //是不是 有RedisCacheGet注解类和这个注解是否开启缓存
            if (!StringUtils.isEmpty(redisCacheGet)&&redisCacheGet.cacheEnable()==true&& !StringUtils.isEmpty(redisCacheGet.key())){
                redisUtil.hset(key,fieldKey,result,redisCacheGet.expire());
            }
        }
        return result;
    }
}
