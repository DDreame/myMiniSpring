package com.minis.beans.factory;

import com.minis.beans.BeanFactory;
import com.minis.beans.factory.support.AbstractFactory;
import com.minis.beans.factory.support.BeanDefinitionRegistry;
import com.minis.exception.BeanException;

/***
 * @description : 简单的 Bean 工厂实现
 * @author : DDDreame
 * @date : 2023/3/26 14:43 
 */
public class SimpleBeanFactory extends DefaultListableBeanFactory {

    public SimpleBeanFactory(){

    }


    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeanException {
        return null;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeanException {
        return null;
    }
}
