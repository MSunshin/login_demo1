package com.lf.hanzhijie.utils;

import cn.hutool.core.util.ObjectUtil;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class RedisCache {
    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    /**
     *指定缓存失效时间
     * @param key
     * @param time
     * @return
     */
    public boolean expire(String key,long time,TimeUnit timeUnit){
        try {
            if (time>0){
                redisTemplate.expire(key,time, timeUnit);
            }
            return true;
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return false;
        }
    }

    /**
     * 设置值
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key,Object value){
        try {
            redisTemplate.opsForValue().set(key,value);
            return true;
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return false;
        }
    }

    /**
     * 通过key获取值
     * @param key
     * @return
     */
    public Object get(String key){
       return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     *设置值并设置过期时间
     * @param key
     * @param value
     * @param time
     * @param timeUnit
     * @return
     */
    public boolean setEx(String key,Object value,long time,TimeUnit timeUnit){
        try {
            if (time > 0){
                redisTemplate.opsForValue().set(key,value,time,timeUnit);
            }else {
                set(key,value);
            }
            return true;
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return false;
        }
    }

    /**
     * 递增
     * @param key
     * @param delta
     * @return
     */
    public long incr(String key,Long delta){
        return redisTemplate.opsForValue().increment(key,delta);
    }
}
