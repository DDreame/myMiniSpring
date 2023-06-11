package com.minis.aop.methodMatcher;

import com.minis.aop.Interceptor.Advisor;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/6/11 16:49 
 */
public interface PointerAdvisor extends Advisor {
    Pointcut getPointcut();
}
