package com.minis.aop.Interceptor;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/6/8 17:19 
 */
public class AfterReturningAdviceInterceptor implements MethodInterceptor, AfterAdvice{
    private final AfterReturningAdvice advice;

    public AfterReturningAdviceInterceptor(AfterReturningAdvice advice) {
        this.advice = advice;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object retVal = invocation.proceed();
        this.advice.afterReturning(retVal, invocation.getMethod(), invocation.getArguments(), invocation.getThis());
        return retVal;
    }
}
