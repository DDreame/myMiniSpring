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
public class SimpleBeanFactory implements BeanFactory {

    public List<BeanDefinition> beanDefinitions = new ArrayList<>();
    public List<String> beanNames = new ArrayList<>();

    public Map<String, Object> singletons = new ConcurrentHashMap<String, Object>();
    @Override
    public Object getBean(String beanName) throws BeanException {
        Object singleton = singletons.get(beanName);
        if(singleton == null){
            int i = beanNames.indexOf(beanName);
            if( i == -1){
                throw new BeanException("No BeanNama:" + beanName);
            }
            else {
                BeanDefinition beanDefinition = beanDefinitions.get(i);
                try{
                    singleton = Class.forName(beanDefinition.getClassName()).newInstance();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                singletons.put(beanName,singleton);
            }
        }
        return singleton;
    }

    @Override
    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        this.beanDefinitions.add(beanDefinition);
        this.beanNames.add(beanDefinition.getId());
    }
}
