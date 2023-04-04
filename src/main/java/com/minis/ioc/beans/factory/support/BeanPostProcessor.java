package com.minis.ioc.beans.factory.support;

import com.minis.ioc.exception.BeanException;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/3/28 23:22 
 */
public interface BeanPostProcessor {

    /**
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeanException
     */
    Object postProcessorBeforeInitialization(Object bean, String beanName) throws BeanException;

    /**
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeanException
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws BeanException;
}
