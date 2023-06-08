package com.minis.aop;

import com.minis.aop.Interceptor.Advisor;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/6/7 11:06 
 */
public class DefaultAopProxyFactory implements AopProxyFactory{

    @Override
    public AopProxy createAopProxy(Object target, Advisor advisor) {
        return new JdkDynamicAopProxy(target, advisor);
    }
}
