package com.minis.ioc.beans.factory.annotation;

import com.minis.ioc.beans.BeanFactory;
import com.minis.ioc.beans.factory.support.BeanPostProcessor;
import com.minis.ioc.exception.BeanException;

import java.lang.reflect.Field;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/3/28 23:29 
 */
public class AutowiredAnnotationBeanPostProcessor implements BeanPostProcessor {

    private BeanFactory beanFactory;

    public AutowiredAnnotationBeanPostProcessor(BeanFactory beanFactory){
        this.beanFactory = beanFactory;
    }

    @Override
    public Object postProcessorBeforeInitialization(Object bean, String beanName) throws BeanException {
        Object result = bean;

        Class<?> clazz = bean.getClass();

        Field[] fields = clazz.getDeclaredFields();
        for(Field field: fields){
            boolean isAutowired = field.isAnnotationPresent(Autowired.class);
            if(isAutowired){
                String filedName = field.getName();
                //过渡写法
                Object autowiredObj = null;
                if(filedName.equals("beanFactory")){
                    autowiredObj = this.beanFactory;
                } else {
                    autowiredObj = this.beanFactory.getBean(filedName);
                }

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
    public BeanFactory getBeanFactory() {
        return beanFactory;
    }
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

}
