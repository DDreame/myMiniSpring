package com.minis.aop.Interceptor;

import java.lang.reflect.Method;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/6/7 15:06 
 */
public class ReflectiveMethodInvocation implements MethodInvocation{
    protected final Object proxy;
    protected final Object target;
    protected final Method method;

    protected Object[] arguments;
    private Class<?> targetClass;

    public ReflectiveMethodInvocation(Object proxy, Object target, Method method, Object[] arguments, Class<?> targetClass) {
        this.proxy = proxy;
        this.target = target;
        this.method = method;
        this.arguments = arguments;
        this.targetClass = targetClass;
    }

    @Override
    public Method getMethod() {
        return method;
    }

    @Override
    public Object[] getArguments() {
        return arguments;
    }

    @Override
    public Object getThis() {
        return this;
    }

    @Override
    public Object proceed() throws Throwable {
        return this.method.invoke(this.target, this.arguments);
    }

}
