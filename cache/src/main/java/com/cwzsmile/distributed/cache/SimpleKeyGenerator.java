package com.cwzsmile.distributed.cache;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author csh9016
 * @date 2019/8/1
 */
@Component("myKeyGenerator")
public class SimpleKeyGenerator implements KeyGenerator {

    @Override
    public Object generate(Object target, Method method, Object... params) {
        return generate(params);
    }

    private static Object generate(Object[] params) {
        User user = (User) params[0];
        return user.getClass();
    }
}
