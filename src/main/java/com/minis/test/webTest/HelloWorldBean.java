package com.minis.test.webTest;

import com.minis.ioc.beans.factory.annotation.Autowired;
import com.minis.test.BaseService;
import com.minis.web.RequestMapping;
import com.minis.web.ResponseBody;
import com.minis.web.servlet.ModelAndView;

import java.util.Date;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/4/4 21:26 
 */
public class HelloWorldBean {
    @RequestMapping("/test")
    public String doGet() {
        return "hello world! I'm doGet";
    }

    @RequestMapping("/test1")
    public String doGet1(Integer number) {
        return "hello world! I'm doGet " + number;
    }


    @RequestMapping("/test2")
    public String doGet2(Date date) {
        return "hello world! I'm doGet " + date;
    }


    @RequestMapping("/test3")
    public String doGet4(String string) {
        return "hello world! I'm doGet " + string;
    }
    public String doPost3() {
        return "hello world!";
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

}
