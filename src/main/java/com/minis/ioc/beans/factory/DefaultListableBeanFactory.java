package com.minis.ioc.beans.factory;

import com.minis.ioc.beans.BeanFactory;
import com.minis.ioc.beans.factory.config.BeanDefinition;
import com.minis.ioc.beans.factory.support.AbstractAutowireCapableBeanFactory;
import com.minis.ioc.beans.factory.support.ConfigurableListableBeanFactory;
import com.minis.ioc.exception.BeanException;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/3/30 20:11 
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements ConfigurableListableBeanFactory {

    private BeanFactory parentBeanFactory;

    @Override
    public int getBeanDefinitionCount() {
        return this.beanDefinitions.size();
    }


    @Override
    public String[] getDependentBeans(String beanName) {
        return this.beanDefinitions.get(beanName).getDependsOn();
    }

    @Override
    public String[] getDependenciesForBean(String beanName) {
        return this.beanDefinitions.get(beanName).getDependsOn();
    }



    @Override
    public String[] getBeanDefinitionNames() {
        return this.beanNames.toArray(new String[0]);
    }


    @Override
    public String[] getBeanNamesForType(Class<?> type) {
        List<String> result = new ArrayList<>();
        for (String beanName : this.beanNames) {
            boolean matchFound = false;
            BeanDefinition mbd = this.getBeanDefinition(beanName);
            Class<?> classToMatch = mbd.getClass();
            if (type.isAssignableFrom(classToMatch)) {
                matchFound = true;
            }
            if (matchFound) {
                result.add(beanName);
            }
        }
        return result.toArray(new String[0]);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> Map<String, T> getBeansOfType(Class type) throws BeanException {
        String[] beanNames = getBeanNamesForType(type);
        Map<String, T> result = new LinkedHashMap<>(beanNames.length);
        for (String beanName : beanNames) {
            Object beanInstance = getBean(beanName);
            result.put(beanName, (T) beanInstance);
        }
        return result;
    }

    public void setParent(BeanFactory beanFactory){
        this.parentBeanFactory = beanFactory;
    }

    @Override
    public Object getBean(String beanName) throws BeanException {
        Object result = super.getBean(beanName);
        if (result == null) {
            result = this.parentBeanFactory.getBean(beanName);
        }

        return result;
    }
}
