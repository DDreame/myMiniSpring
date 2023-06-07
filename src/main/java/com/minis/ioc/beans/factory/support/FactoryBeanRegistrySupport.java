package com.minis.ioc.beans.factory.support;

import com.minis.ioc.beans.factory.DefaultSingletonBeanRegistry;
import com.minis.ioc.beans.factory.FactoryBean;
import com.minis.ioc.exception.BeanException;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/6/7 10:25 
 */
public abstract class FactoryBeanRegistrySupport extends DefaultSingletonBeanRegistry {
    protected Class<?> getTypeForFactoryBean(final FactoryBean<?> factoryBean){
        return factoryBean.getObjectType();
    }

    protected Object getObjectFromFactoryBean(FactoryBean<?> factory, String beanName){
        Object object = doGetObjectFromFactoryBean(factory, beanName);
        try{
            object = postProcessObjectFromFactoryBean(object, beanName);
        }catch (BeanException e){
            e.printStackTrace();
        }
        return object;
    }

    //后处理
    private Object postProcessObjectFromFactoryBean(Object object, String beanName) throws BeanException{
        return object;
    }

    private Object doGetObjectFromFactoryBean(final FactoryBean<?> factory, final String beanName){
        Object object = null;
        try {
            object = factory.getObject();
        }catch (Exception e){
            e.printStackTrace();
        }
        return object;
    }
}
