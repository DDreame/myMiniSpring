package com.minis.beans.factory;

import com.minis.beans.BeanFactory;
import com.minis.beans.factory.config.*;
import com.minis.beans.factory.support.BeanDefinitionRegistry;
import com.minis.beans.factory.support.DefaultSingletonBeanRegistry;
import com.minis.exception.BeanException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
