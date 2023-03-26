package com.minis.context;

import com.minis.beans.*;
import com.minis.core.ClassPathXmlResource;
import com.minis.core.Resource;
import com.minis.beans.SimpleBeanFactory;
import com.minis.core.XmlBeanDefinitionReader;
import com.minis.exception.BeanException;

/***
 * @description : 一个 Bean 管理器 目前是单例的
 * @author : DDDreame
 * @date : 2023/3/21 00:46 
 */
public class ClassPathXmlApplicationContent {

    BeanFactory beanFactory;

    public ClassPathXmlApplicationContent(String fileName){
        Resource resource = new ClassPathXmlResource(fileName);
        BeanFactory beanFactory = new SimpleBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinition(resource);
        this.beanFactory = beanFactory;
    }



    public Object getBean(String beanName) throws BeanException {
        return beanFactory.getBean(beanName);
    }

    public void registerBeanDefinition(BeanDefinition beanDefinition){
        this.beanFactory.registerBeanDefinition(beanDefinition);
    }


}
