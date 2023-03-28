package com.minis.beans.factory.support;

import com.minis.beans.factory.AutowireCapableBeanFactory;
import com.minis.beans.factory.annotation.Autowired;
import com.minis.exception.BeanException;

import java.lang.reflect.Field;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/3/28 23:29 
 */
public class AutowiredAnnotationBeanPostProcessor implements BeanPostProcessor{

    private AutowireCapableBeanFactory beanFactory;

    @Override
    public Object postProcessorBeforeInitialization(Object bean, String beanName) throws BeanException {
        Object result = bean;

        Class<?> clazz = bean.getClass();

        Field[] fields = clazz.getDeclaredFields();

        for(Field field: fields){
            boolean isAutowired = field.isAnnotationPresent(Autowired.class);
            if(isAutowired){
                String filedName = field.getName();
                Object autowiredObj = this.beanFactory.getBean(filedName);
                try {
                    field.setAccessible(true);
                    field.set(bean, autowiredObj);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return result;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeanException {
        return bean;
    }
    public AutowireCapableBeanFactory getBeanFactory() {
        return beanFactory;
    }
    public void setBeanFactory(AutowireCapableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

}
