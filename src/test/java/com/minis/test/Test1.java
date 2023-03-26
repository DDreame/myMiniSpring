package com.minis.test;

import com.minis.ClassPathXmlApplicationContent;

/***
 * @description : Todo
 * @author : 梦某人
 * @date : 2023/3/26 13:38 
 */
public class Test1 {

    public static void main(String[] args) {
        ClassPathXmlApplicationContent content = new ClassPathXmlApplicationContent("myBean.xml");
        AService aService = (AService) content.getBean("aservice");
        aService.SayHello();
    }
}
