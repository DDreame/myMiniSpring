package com.minis.web;

import java.net.URL;
import java.util.Iterator;
import java.util.List;

import com.minis.ioc.core.Resource;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/4/4 21:34 
 */
public class ClassPathXmlResource implements Resource {
    Document document;
    Element rootElement;
    Iterator<Element> elementIterator;

    public ClassPathXmlResource(URL xmlPath) {
        SAXReader saxReader=new SAXReader();
        try {
            this.document = saxReader.read(xmlPath);
            this.rootElement=document.getRootElement();
            this.elementIterator=this.rootElement.elementIterator();
        } catch (DocumentException e) {
            e.printStackTrace();
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