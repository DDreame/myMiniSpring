package com.minis.aop;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/6/7 11:06 
 */
public class DefaultAopProxyFactory implements AopProxyFactory{

    @Override
    public AopProxy createAopProxy(Object target) {
        return new JdkDynamicAopProxy(target);
    }
}
