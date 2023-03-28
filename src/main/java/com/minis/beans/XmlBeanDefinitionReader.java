package com.minis.beans;

import com.minis.beans.BeanDefinition;
import com.minis.beans.BeanFactory;
import com.minis.core.Resource;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/3/26 14:39 
 */
public class XmlBeanDefinitionReader {
    SimpleBeanFactory beanFactory;

    public XmlBeanDefinitionReader(SimpleBeanFactory beanFactory){
        this.beanFactory = beanFactory;
    }

    public void loadBeanDefinition(Resource resource){
        while(resource.hasNext()){
            Element element = (Element) resource.next();
            String beanId = element.attributeValue("id");
            String className = element.attributeValue("class");
            BeanDefinition beanDefinition = new BeanDefinition(beanId,className);

            //处理属性
            List<Element> pvEle = element.elements("property");
            PropertyValues pvs = new PropertyValues();
            for(Element e: pvEle){
                String type = e.attributeValue("type");
                String name = e.attributeValue("name");
                String value = e.attributeValue("value");
                pvs.addPropertyValue(new PropertyValue(type, name, value));
            }
            beanDefinition.setPropertyValues(pvs);

            //处理构造器参数
            List<Element> avsEle = element.elements("constructor-arg");
            ArgumentValues avs = new ArgumentValues();
            for(Element e: avsEle){
                String type = e.attributeValue("type");
                String name = e.attributeValue("name");
                String value = e.attributeValue("value");
                avs.addGenericArgumentValue(new ArgumentValue(value, type, name));
            }
            beanDefinition.setArgumentValues(avs);

            this.beanFactory.registerBeanDefinition(beanDefinition);

        }
    }
}
