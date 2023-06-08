package com.minis.aop.Interceptor;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/6/7 14:57 
 */
public interface Advisor {
    MethodInterceptor getMethodInterceptor();
    void setMethodInterceptor(MethodInterceptor methodInterceptor);
}
