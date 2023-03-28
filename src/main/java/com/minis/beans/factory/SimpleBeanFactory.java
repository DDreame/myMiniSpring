package com.minis.beans.factory;

import com.minis.exception.BeanException;

/***
 * @description : 简单的 Bean 工厂实现
 * @author : DDDreame
 * @date : 2023/3/26 14:43 
 */
public class SimpleBeanFactory extends AbstractFactory {

    public SimpleBeanFactory(){

    }


    @Override
    public Object applyBeanPostProcessorBeforeInitialization(Object existingBean, String beanName) throws BeanException {
        return null;
    }

    @Override
    public Object applyBeanPostProcessorAfterInitialization(Object existingBean, String beanName) throws BeanException {
        return null;
    }
}
