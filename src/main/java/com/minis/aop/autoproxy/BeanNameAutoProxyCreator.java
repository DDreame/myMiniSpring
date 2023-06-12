package com.minis.aop.autoproxy;

import com.minis.aop.AopProxyFactory;
import com.minis.aop.DefaultAopProxyFactory;
import com.minis.aop.ProxyFactoryBean;
import com.minis.aop.methodMatcher.PointerAdvisor;
import com.minis.ioc.beans.BeanFactory;
import com.minis.ioc.beans.factory.FactoryBean;
import com.minis.ioc.beans.factory.support.BeanPostProcessor;
import com.minis.ioc.exception.BeanException;
import com.minis.utils.PatternMatchUtils;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/6/12 09:10 
 */
public class BeanNameAutoProxyCreator implements BeanPostProcessor {

    String pattern;
    private BeanFactory beanFactory;
    private AopProxyFactory aopProxyFactory;
    private String interceptorName;
    private PointerAdvisor advisor;

    public BeanNameAutoProxyCreator(){
        this.aopProxyFactory = new DefaultAopProxyFactory();
    }
    @Override
    public Object postProcessorBeforeInitialization(Object bean, String beanName) throws BeanException {
        if(bean instanceof FactoryBean || !isMatch(beanName, this.pattern)){
            return bean;
        }
        else{
            ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
            proxyFactoryBean.setTarget(bean);
            proxyFactoryBean.setBeanFactory(beanFactory);
            proxyFactoryBean.setAopProxyFactory(aopProxyFactory);
            proxyFactoryBean.setInterceptorName(interceptorName);
            return proxyFactoryBean;
        }
    }

    protected boolean isMatch(String beanName, String mappedName){
        return PatternMatchUtils.simpleMatch(mappedName, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeanException {
        return bean;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }


    public String getInterceptorName() {
        return interceptorName;
    }

    public void setInterceptorName(String interceptorName) {
        this.interceptorName = interceptorName;
    }

    public PointerAdvisor getAdvisor() {
        return advisor;
    }

    public void setAdvisor(PointerAdvisor advisor) {
        this.advisor = advisor;
    }
}
