package com.minis.test.aop;

import com.minis.aop.Interceptor.AfterReturningAdvice;

import java.lang.reflect.Method;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/6/8 17:25 
 */
public class MyAfterAdvice implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) {
        System.out.println("------------my interceptor after method call --------------------");
    }
}
