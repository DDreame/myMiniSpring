package com.minis.test.webTest;

import com.minis.web.RequestMapping;

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
    public String doPost() {
        return "hello world!";
    }
}
