package com.minis.test.aop;

import com.minis.aop.Interceptor.MethodBeforeAdvice;

import java.lang.reflect.Method;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/6/8 17:25 
 */
public class MyBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("---------------my interceptor before method call -----------------");
    }
}
