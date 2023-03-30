package com.minis.beans.factory.support;

import com.minis.beans.BeanFactory;
import com.minis.exception.BeanException;

import java.util.Map;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/3/30 19:32 
 */
public interface ListableBeanFactory extends BeanFactory {

    boolean containsBeanDefinition(String beanName);

    int getBeanDefinitionCount();

    String[] getBeanDefinitionNames();

    String[] getBeanNamesForType(Class<?> type);

    <T> Map<String, T> getBeansOfType(Class type) throws BeanException;


}
