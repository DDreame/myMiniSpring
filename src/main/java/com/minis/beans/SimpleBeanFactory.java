package com.minis.beans;

import com.minis.beans.BeanDefinition;
import com.minis.beans.BeanFactory;
import com.minis.exception.BeanException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/***
 * @description : 简单的 Bean 工厂实现
 * @author : DDDreame
 * @date : 2023/3/26 14:43 
 */
public class SimpleBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    public SimpleBeanFactory(){

    }

    public Map<String, BeanDefinition> beanDefinitions = new ConcurrentHashMap<>(256);

    @Override
    public Object getBean(String beanName) throws BeanException {
        Object singleton = singletons.get(beanName);
        if(singleton == null){
            BeanDefinition beanDefinition = beanDefinitions.get(beanName);
            if(beanDefinition == null){
                throw new BeanException("No BeanNama:" + beanName);
            }

            try{
                singleton = Class.forName(beanDefinition.getClassName()).newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            this.registerSingleton(beanName,singleton);

        }
        return singleton;
    }

    @Override
    public Boolean containsBean(String beanName) {
        return beanDefinitions.containsKey(beanName);
    }

    @Override
    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        beanDefinitions.put(beanDefinition.getId(),beanDefinition);
    }


}
