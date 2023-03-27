package com.minis.beans;

import com.minis.exception.BeanException;

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
}
