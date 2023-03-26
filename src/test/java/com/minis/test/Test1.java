package com.minis.test;

import com.minis.context.ClassPathXmlApplicationContent;
import com.minis.exception.BeanException;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/3/26 13:38 
 */
public class Test1 {

    public static void main(String[] args) throws BeanException {
        ClassPathXmlApplicationContent content = new ClassPathXmlApplicationContent("myBean.xml");
        AService aService = (AService) content.getBean("aservice");
        aService.SayHello();
    }
}
