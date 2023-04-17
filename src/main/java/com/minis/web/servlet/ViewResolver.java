package com.minis.web.servlet;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/4/17 23:06 
 */
public interface ViewResolver {
    View resolveViewName(String viewName) throws Exception;
}
