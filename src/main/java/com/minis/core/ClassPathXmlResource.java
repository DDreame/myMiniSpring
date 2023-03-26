package com.minis.core;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.net.URL;
import java.util.Iterator;

/***
 * @description : 加载资源类
 * @author : DDDreame
 * @date : 2023/3/26 14:33 
 */
public class ClassPathXmlResource implements Resource{

    Document document;
    Element rootElement;
    Iterator<Element> elementIterator;

    public ClassPathXmlResource(String fileName){
        SAXReader saxReader = new SAXReader();
        URL url = this.getClass().getClassLoader().getResource(fileName);
        try{
            this.document = saxReader.read(url);
            this.rootElement = document.getRootElement();
            this.elementIterator = rootElement.elementIterator();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @Override
    public boolean hasNext() {
        return this.elementIterator.hasNext();
    }

    @Override
    public Object next() {
        return this.elementIterator.next();
    }
}
