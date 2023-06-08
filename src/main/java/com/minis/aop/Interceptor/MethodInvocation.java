package com.minis.aop.Interceptor;

import java.lang.reflect.Method;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/6/7 14:52 
 */
public interface MethodInvocation {
    Method getMethod();
    Object[] getArguments();
    Object getThis();
    Object proceed() throws  Throwable;
}
