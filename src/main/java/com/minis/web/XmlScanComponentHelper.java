package com.minis.web;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/4/5 14:39 
 */
public class XmlScanComponentHelper {

    public static List<String> getNodeValue(URL xmlPath){
        List<String> packages = new ArrayList<>();
        SAXReader saxReader = new SAXReader();
        Document document = null;
        try {
            document = saxReader.read(xmlPath); //加载配置文件
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        assert document != null;
        Element root = document.getRootElement();
        Iterator it = root.elementIterator();
        while (it.hasNext()) {
            //得到XML中所有的base-package节点
            Element element = (Element) it.next();
            packages.add(element.attributeValue("base-package"));
        }
        return packages;

    }
}
