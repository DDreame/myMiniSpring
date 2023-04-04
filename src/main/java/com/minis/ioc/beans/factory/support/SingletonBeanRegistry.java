package com.minis.ioc.beans.factory.support;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/3/27 22:38 
 */
public interface SingletonBeanRegistry {
    void registerSingleton(String beanName, Object singletonObject);

    Object getSingleton(String beanName);

    boolean containsSingleton(String beanName);

    String[] getSingletonNames();

}
