package com.minis;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/***
 * @description : Todo
 * @author : 梦某人
 * @date : 2023/3/21 00:46 
 */
public class ClassPathXmlApplicationContent {


    private List<BeanDefinition> beanDefinitions = new ArrayList<>();

    private ConcurrentHashMap<String, Object> singletons = new ConcurrentHashMap<String, Object>();

    public ClassPathXmlApplicationContent(String fileName){
        this.readXml(fileName);
        this.instanceBeans();
    }

    private void readXml(String fileName){
        SAXReader saxReader = new SAXReader();
        try{
            URL xmlPath = this.getClass().getClassLoader().getResource(fileName);
            Document document = saxReader.read(xmlPath);
            Element rootElement = document.getRootElement();
            for (Element element: rootElement.elements()) {
                String beanID = element.attributeValue("id");
                String className = element.attributeValue("class");
                BeanDefinition beanDefinition = new BeanDefinition(beanID,className);
                beanDefinitions.add(beanDefinition);
            }

        }catch (Exception e){
            System.out.println("Read Xml file failed!");
        }
    }
    private void instanceBeans(){
        for(BeanDefinition beanDefinition: beanDefinitions){
            try{
                singletons.put(beanDefinition.getId(),
                        Class.forName(beanDefinition.getClassName()).newInstance());
            }catch(Exception e){
                System.out.println("bean create error:" + beanDefinition.getId());
            }
        }

    }
    public Object getBean(String beanName){
        return singletons.get(beanName);
    }


}
