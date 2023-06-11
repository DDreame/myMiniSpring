package com.minis.aop.methodMatcher;

import java.lang.reflect.Method;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/6/11 16:48 
 */
public interface MethodMatcher {
    boolean matcher(Method method, Class<?> targetClass);
}
