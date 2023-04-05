package com.minis.web;

import com.minis.ioc.context.ClassPathXmlApplicationContent;

import javax.servlet.ServletContext;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/4/5 19:55 
 */
public class XmlWebApplicationContext extends ClassPathXmlApplicationContent implements WebApplicationContext {

    private ServletContext servletContext;

    public XmlWebApplicationContext(String fileName) {
        super(fileName);
    }


    @Override
    public ServletContext getServletContext() {
        return this.servletContext;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}
