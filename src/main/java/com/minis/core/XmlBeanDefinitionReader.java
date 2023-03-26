package com.minis.core;

import com.minis.beans.BeanDefinition;
import com.minis.beans.BeanFactory;
import com.minis.core.Resource;
import org.dom4j.Element;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/3/26 14:39 
 */
public class XmlBeanDefinitionReader {
    BeanFactory beanFactory;

    public XmlBeanDefinitionReader(BeanFactory beanFactory){
        this.beanFactory = beanFactory;
    }

    public void loadBeanDefinition(Resource resource){
        while(resource.hasNext()){
            Element element = (Element) resource.next();
            String beanId = element.attributeValue("id");
            String className = element.attributeValue("class");
            BeanDefinition beanDefinition = new BeanDefinition(beanId,className);
            this.beanFactory.registerBeanDefinition(beanDefinition);

        }
    }
}
