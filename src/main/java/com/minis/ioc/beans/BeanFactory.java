package com.minis.ioc.beans;

import com.minis.ioc.beans.factory.config.BeanDefinition;
import com.minis.ioc.exception.BeanException;

/***
 * @description : Bean 工厂，负责注册 Bean 和 获取 Bean
 * @author : DDDreame
 * @date : 2023/3/26 14:21 
 */
public interface BeanFactory {

    /**
     * 根据 beanName 返回对应的 bean实例
     * @param beanName
     * @return Object bean实例
     * @throws BeanException
     */
    Object getBean(String beanName) throws BeanException;

    /**
     *
     * @param beanName
     * @return
     */
    Boolean containsBean(String beanName);
    /**
     * 向 BeanFactory 内定义一个 Bean
     * @param beanDefinition
     */
    void registerBeanDefinition(BeanDefinition beanDefinition);

    /**
     * 是否单例 Bean
     * @param beanName
     * @return
     */
    boolean isSingleton(String beanName);

    /**
     *
     * @param beanName
     * @return
     */
    boolean isPrototype(String beanName);

    /**
     *
     * @param name
     * @return
     */

    Class<?> getType(String name);

}
