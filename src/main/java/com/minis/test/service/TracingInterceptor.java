package com.minis.test.service;

import com.minis.aop.Interceptor.MethodInterceptor;
import com.minis.aop.Interceptor.MethodInvocation;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/6/7 14:54 
 */
public class TracingInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable{
        System.out.println("Method " + invocation.getMethod() +" is called on"
                + invocation.getThis() + " with args " + invocation.getArguments());
        Object ret = invocation.proceed();
        System.out.println("Method " + invocation.getMethod() + " returns " + ret);
        return ret;
    }
}
