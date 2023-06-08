package com.minis.aop.Interceptor;

import java.lang.reflect.Method;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/6/8 16:52 
 */
public interface AfterReturningAdvice extends AfterAdvice{
    void afterReturning(Object returnValue, Method method, Object[] args, Object target);
}
