package com.minis.web.servlet;

import com.minis.ioc.exception.BeanException;
import com.minis.web.WebApplicationContext;
import com.minis.test.WebBindingInitializer;
import com.minis.web.databind.WebDataBinder;
import com.minis.web.databind.WebDataBinderFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/4/5 20:31 
 */
public class RequestMappingHandlerAdapter implements HandlerAdapter{
    WebApplicationContext wac;
    WebBindingInitializer webBindingInitializer;

    public RequestMappingHandlerAdapter(WebApplicationContext wac){
        this.wac = wac;
        try {
            this.webBindingInitializer = (WebBindingInitializer) this.wac.getBean("com.minis.test.webTest.DateInitializer");
        } catch (BeanException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        handleInternal(request, response, (HandlerMethod) handler);
    }

    private void handleInternal(HttpServletRequest request, HttpServletResponse response, HandlerMethod handler) throws Exception {
        WebDataBinderFactory binderFactory = new WebDataBinderFactory();
        Parameter[] methodParameters = handler.getMethod().getParameters();
        Object[] methodParamObjs = new Object[methodParameters.length];
        int i = 0;
        for(Parameter methodParameter: methodParameters){
            Object methodParamObj = methodParameter.getType().newInstance();
            WebDataBinder wdb = binderFactory.createBinder(request, methodParamObj, methodParameter.getName());
            webBindingInitializer.initBinder(wdb);
            wdb.bind(request);
            methodParamObjs[i] = methodParamObj;
            i ++;
        }
        Method invocableMethod = handler.getMethod();
        try {
            Object resultObj = invocableMethod.invoke(handler.getBean(), methodParamObjs);
            response.getWriter().append(resultObj.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
