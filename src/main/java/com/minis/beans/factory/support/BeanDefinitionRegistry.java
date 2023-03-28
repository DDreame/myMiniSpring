package com.minis.beans.factory.support;

import com.minis.beans.factory.config.BeanDefinition;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/3/28 14:39 
 */
public interface BeanDefinitionRegistry {
    void registerBeanDefinition(String name, BeanDefinition beanDefinition);

    void removeBeanDefinition(String name);

    BeanDefinition getBeanDefinition(String name);

    boolean containsBeanDefinition(String name);
}
