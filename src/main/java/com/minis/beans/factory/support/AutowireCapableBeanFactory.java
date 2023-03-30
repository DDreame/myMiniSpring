package com.minis.beans.factory.support;

import com.minis.beans.BeanFactory;
import com.minis.exception.BeanException;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/3/30 19:59 
 */
public interface AutowireCapableBeanFactory extends BeanFactory {
    int AUTOWIRE_NO = 0;
    int AUTOWIRE_BY_NAME = 1;
    int AUTOWIRE_BY_TYPE = 2;
    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeanException;
    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeanException;

}
