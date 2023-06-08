package com.minis.aop;

import com.minis.aop.Interceptor.Advisor;
import com.minis.aop.Interceptor.MethodInterceptor;
import com.minis.ioc.beans.BeanFactory;
import com.minis.ioc.beans.factory.FactoryBean;
import com.minis.ioc.beans.factory.annotation.Autowired;
import com.minis.ioc.exception.BeanException;
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
    @Autowired
    private BeanFactory beanFactory;
    private String interceptorName;
    private Advisor advisor;
    public ProxyFactoryBean(){
        this.aopProxyFactory = new DefaultAopProxyFactory();
    }
    private synchronized void initializeAdvisor(){
        MethodInterceptor advice = null;
        MethodInterceptor mi = null;
        try {
            advice = (MethodInterceptor) this.beanFactory.getBean(this.interceptorName);
        }catch (BeanException e){
            e.printStackTrace();
        }
        advisor = new DefaultAdvisor();
        advisor.setMethodInterceptor(advice);
    }
    public AopProxyFactory getAopProxyFactory() {
        return aopProxyFactory;
    }

    protected AopProxy createAopProxy(){
        return getAopProxyFactory().createAopProxy(target, advisor);
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

    public String getInterceptorName() {
        return interceptorName;
    }

    public void setInterceptorName(String interceptorName) {
        this.interceptorName = interceptorName;
    }


    @Override
    public Object getObject() throws Exception{
        initializeAdvisor();
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

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }
}
