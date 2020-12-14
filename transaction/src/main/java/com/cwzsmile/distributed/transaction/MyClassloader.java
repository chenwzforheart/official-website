package com.cwzsmile.distributed.transaction;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

/**
 * @author csh9016
 * @date 2020/12/14
 */
public class MyClassloader extends URLClassLoader {

    Map<String, Class<?>> classMap = new HashMap<>();

    public MyClassloader(URL[] urls) {
        super(urls);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        Class<?> classLoaded = classMap.get(name);
        if (classLoaded != null) {
            return classLoaded;
        }
        Class<?> findClass = null;
        try {
            findClass = findClass(name);
        } catch (Exception e) {
            //还可以从父类查找，这个异常吞掉，如果没有父类会抛出
        }
        if (findClass != null) {
            classMap.put(name, findClass);
            return findClass;
        }
        return super.loadClass(name);
    }

    @Override
    protected Package getPackage(String name) {
        return super.getPackage(name);
    }
}
