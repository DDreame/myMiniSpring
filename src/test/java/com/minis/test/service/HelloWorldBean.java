package com.minis.test.service;

import com.minis.ioc.beans.factory.annotation.Autowired;
import com.minis.test.aop.IAction;
import com.minis.test.aop.DynamicProxy;
import com.minis.test.aop.IPlay;
import com.minis.web.RequestMapping;
import com.minis.web.ResponseBody;
import com.minis.web.servlet.ModelAndView;
import com.minis.test.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/4/4 21:26 
 */
public class HelloWorldBean {

    @Autowired
    UserService userService;

    @RequestMapping("/test")
    public ModelAndView doGet() {
        return new ModelAndView("test","msg","hello world! I'm doGet");
    }

    @RequestMapping("/test1")
    public ModelAndView doGet1(Integer number) {
        return new ModelAndView("test","msg","hello world! I'm doGet " + number);
    }


    @RequestMapping("/test2")
    public ModelAndView doGet2(Date date) {
        return new ModelAndView("test","msg","hello world! I'm doGet " + date);
    }


    @RequestMapping("/test3")
    public ModelAndView doGet4(String string) {
        System.out.println(string);
        return new ModelAndView("test","msg","hello world! I'm doGet " + string);
    }
    public ModelAndView doPost3() {
        return new ModelAndView("test","msg","hello world!");
    }
    @RequestMapping("/test5")
    public ModelAndView doTest5(User user) {
        return new ModelAndView("test","msg",user.getName() + " is coming!");
    }

    @RequestMapping("/test6")
    public String doTest6(User user) {
        return "error";
    }

    @RequestMapping("/test7")
    @ResponseBody
    public User doTest7(User user) {
        user.setName(user.getName() + "---");
        user.setBirthday(new Date());
        return user;
    }

    @RequestMapping("/test8")
    @ResponseBody
    public User doTest8() {
        return userService.getUserInfo(1);
    }

    @RequestMapping("/test9")
    @ResponseBody
    public User doTest9() {
        return userService.getUserInfo2(2);
    }

    @RequestMapping("/test10")
    @ResponseBody
    public List<User> doTest10() {
        return userService.getUserInfo3(1);
    }

    @RequestMapping("/test11")
    @ResponseBody
    public User doTest11() {
        return userService.getUserInfo4(1);
    }


    @Autowired
    IAction action;
    @RequestMapping("/testaop")
    public void doTestAop() {
        DynamicProxy proxy = new DynamicProxy(action);
        IAction p = (IAction)proxy.getProxy();
        p.doAction();
        String str = "test aop1, hello world!";
        System.out.println(str);
    }

    @RequestMapping("/testaop2")
    public void doTestRealAop() {
        action.doAction();
        String str = "test aop2, hello world!";
        System.out.println(str);
    }
    @Autowired
    IAction action2;
    @RequestMapping("/testaop3")
    public void doTestRealAop3() {
        String str = "________test aop3, hello world!_______";
        System.out.println(str);
        action2.doAction();
    }


    @Autowired
    IAction action3;
    @RequestMapping("/testaop4")
    public void doTestRealAop4() {
        String str = "________test aop4, hello world!_______";
        System.out.println(str);
        action3.doAction();
        action3.doSome();
    }

    @Autowired
    IPlay play1;
    @Autowired
    IPlay play2;
    @Autowired
    IPlay play3;
    @RequestMapping("/testaop5")
    public void doTestRealAop5() {
        String str = "________test aop4, hello world!_______";
        System.out.println(str);
        play1.doPlay();
        play2.doPlay();
        play3.doPlay();
    }

}
