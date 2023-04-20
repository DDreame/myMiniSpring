package com.minis.test.service;

import com.minis.ioc.beans.factory.annotation.Autowired;
import com.minis.test.BaseService;
import com.minis.test.service.UserService;
import com.minis.web.RequestMapping;
import com.minis.web.ResponseBody;
import com.minis.web.servlet.ModelAndView;
import com.minis.test.entity.User;

import java.util.Date;

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
    public User doTest9(Integer id) {
        return userService.getUserInfo2(id);
    }

}
