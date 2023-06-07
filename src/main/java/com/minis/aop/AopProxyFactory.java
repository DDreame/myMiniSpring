package com.minis.aop;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/6/7 11:00 
 */
public interface AopProxyFactory {
    AopProxy createAopProxy(Object target);
}
