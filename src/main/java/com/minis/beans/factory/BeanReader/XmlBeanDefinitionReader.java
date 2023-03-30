package com.minis.beans.factory.BeanReader;

import com.minis.beans.factory.support.AbstractFactory;
import com.minis.beans.factory.config.*;
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
    AbstractFactory beanFactory;

    public XmlBeanDefinitionReader(AbstractFactory beanFactory){
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
            List<String> refs = new ArrayList<>();
            for(Element e: pvEle){
                String type = e.attributeValue("type");
                String name = e.attributeValue("name");
                String value = e.attributeValue("value");
                String ref = e.attributeValue("ref");
                boolean isRef = false;
                String pV = "";
                if(value != null && !"".equals(value)){
                    pV = value;
                } else if (ref != null && !"".equals(ref)) {
                    isRef = true;
                    pV = ref;
                    refs.add(ref);
                }
                pvs.addPropertyValue(new PropertyValue(type, name, pV,isRef));
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

            String[] refAry = refs.toArray(new String[0]);
            beanDefinition.setDependsOn(refAry);

            this.beanFactory.registerBeanDefinition(beanDefinition);

        }
    }
}
