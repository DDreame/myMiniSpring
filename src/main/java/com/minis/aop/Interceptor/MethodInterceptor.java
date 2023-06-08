package com.minis.aop.Interceptor;

import com.minis.aop.Interceptor.MethodInvocation;
/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/6/7 14:51 
 */
public interface MethodInterceptor extends Interceptor{
    Object invoke(MethodInvocation invocation) throws Throwable;
}
