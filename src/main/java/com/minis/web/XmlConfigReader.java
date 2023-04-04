package com.minis.web;

import com.minis.ioc.core.Resource;
import com.minis.web.MappingValue;
import org.dom4j.Element;

import java.util.HashMap;
import java.util.Map;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/4/4 20:31 
 */
public class XmlConfigReader {
    public XmlConfigReader(){

    }
    public Map<String, MappingValue> loadConfig(Resource resource){
        Map<String, MappingValue> result = new HashMap<String, MappingValue>();
        while(resource.hasNext()){
            Element element = (Element) resource.next();
            String beanID=element.attributeValue("id");
            String beanClassName=element.attributeValue("class");
            String beanMethod=element.attributeValue("value");
            result.put(beanID, new MappingValue(beanID,beanClassName,beanMethod));
        }
        return result;
    }
}
