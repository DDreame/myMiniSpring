package com.minis.web;

import javax.servlet.ServletContext;
import com.minis.ioc.context.ApplicationContext;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/4/5 15:32 
 */
public interface WebApplicationContext extends ApplicationContext {

    String ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE = WebApplicationContext.class.getName() + ".ROOT";
    ServletContext getServletContext();
    void setServletContext(ServletContext servletContext);
}
