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
        AService aService2 = (AService) content.getBean("aservice2");
        aService2.SayHello();

        //依赖注入
        System.out.println("开始测试依赖注入！");
        BaseBaseService baseService = (BaseBaseService) content.getBean("basebaseservice");
        baseService.sayHello();
    }
}
