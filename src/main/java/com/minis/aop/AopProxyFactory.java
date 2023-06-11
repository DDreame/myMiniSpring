package com.minis.aop;

import com.minis.aop.Interceptor.Advisor;
import com.minis.aop.methodMatcher.PointerAdvisor;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/6/7 11:00 
 */
public interface AopProxyFactory {
    AopProxy createAopProxy(Object target, PointerAdvisor advisor);
}
