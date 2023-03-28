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
        // Bean 加载
        System.out.println("工厂加载 Bean 定义");
        ClassPathXmlApplicationContent content = new ClassPathXmlApplicationContent("myBean.xml");
        // 控制反转
        System.out.println("索要 Bean 实例");
        AService aService = (AService) content.getBean("aservice");
        aService.SayHello();
        AService aService2 = (AService) content.getBean("aservice2");
        aService2.SayHello();

        //依赖注入
        System.out.println("开始测试依赖注入！");
        BaseBaseService baseService = (BaseBaseService) content.getBean("basebaseservice");
        baseService.sayHello();

        //注解测试
        System.out.println("测试注解注入");
        BaseService base = (BaseService) content.getBean("baseservice");
        base.sayHello();
    }
}
