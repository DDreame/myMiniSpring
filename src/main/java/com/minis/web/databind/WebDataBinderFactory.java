package com.minis.web.databind;

import javax.servlet.http.HttpServletRequest;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/4/6 20:04 
 */
public class WebDataBinderFactory {
    public WebDataBinder createBinder(HttpServletRequest request, Object target, String objectName){
        WebDataBinder wbd = new WebDataBinder(target, objectName);
        initBinder(wbd, request);
        return wbd;
    }

    protected void initBinder(WebDataBinder dataBinder, HttpServletRequest request){

    }
}
