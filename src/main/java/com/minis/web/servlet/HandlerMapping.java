package com.minis.web.servlet;

import javax.servlet.http.HttpServletRequest;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/4/5 20:07 
 */
public interface HandlerMapping {
    HandlerMethod getHandler(HttpServletRequest request) throws Exception;

}
