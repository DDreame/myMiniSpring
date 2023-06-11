package com.minis.aop;

import com.minis.aop.Interceptor.Advisor;
import com.minis.aop.Interceptor.MethodInterceptor;
import com.minis.aop.Interceptor.MethodInvocation;
import com.minis.aop.Interceptor.ReflectiveMethodInvocation;
import com.minis.aop.methodMatcher.PointerAdvisor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/6/7 11:01 
 */
public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {
    Object target;
    PointerAdvisor advisor;

    public JdkDynamicAopProxy(Object target, PointerAdvisor advisor) {
        this.target = target;
        this.advisor = advisor;
    }

    @Override
    public Object getProxy() {
        Object obj = Proxy.newProxyInstance(JdkDynamicAopProxy.class.getClassLoader(),
                target.getClass().getInterfaces(), this);
        return obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class<?> targetClass = (target != null ? target.getClass() : null);
        if(this.advisor.getPointcut().getMethodMatcher().matcher(method,targetClass)){
            MethodInterceptor interceptor = this.advisor.getMethodInterceptor();
            MethodInvocation invocation =
                    new ReflectiveMethodInvocation(proxy, target, method, args, targetClass);

            return interceptor.invoke(invocation);
        }
        return null;
    }
}
