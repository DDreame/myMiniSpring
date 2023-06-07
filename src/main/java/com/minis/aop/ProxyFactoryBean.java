package com.minis.aop;

import com.minis.ioc.beans.factory.FactoryBean;
import com.minis.utils.ClassUtils;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/6/7 10:42 
 */
public class ProxyFactoryBean implements FactoryBean<Object>{
    private AopProxyFactory aopProxyFactory;
    private String[] interceptorNames;
    private String targetName;
    private Object target;
    private ClassLoader proxyClassLoader = ClassUtils.getDefaultClassLoader();
    private Object singletonInstance;
    public ProxyFactoryBean(){
        this.aopProxyFactory = new DefaultAopProxyFactory();
    }

    public AopProxyFactory getAopProxyFactory() {
        return aopProxyFactory;
    }

    protected AopProxy createAopProxy(){
        return getAopProxyFactory().createAopProxy(target);
    }
    public void setAopProxyFactory(AopProxyFactory aopProxyFactory) {
        this.aopProxyFactory = aopProxyFactory;
    }

    public String[] getInterceptorNames() {
        return interceptorNames;
    }

    public void setInterceptorNames(String[] interceptorNames) {
        this.interceptorNames = interceptorNames;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public ClassLoader getProxyClassLoader() {
        return proxyClassLoader;
    }

    public void setProxyClassLoader(ClassLoader proxyClassLoader) {
        this.proxyClassLoader = proxyClassLoader;
    }

    @Override
    public Object getObject() throws Exception{
        return getSingletonInstance();
    }
    private synchronized Object getSingletonInstance(){
        if(this.singletonInstance == null){
            this.singletonInstance = getProxy(createAopProxy());
        }
        return this.singletonInstance;
    }
    protected Object getProxy(AopProxy aopProxy){
        return aopProxy.getProxy();
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }
}
