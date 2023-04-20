package com.minis.web.servlet;

import com.minis.ioc.exception.BeanException;
import com.minis.web.ResponseBody;
import com.minis.web.WebApplicationContext;
import com.minis.web.databind.WebBindingInitializer;
import com.minis.web.converter.HttpMessageConverter;
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
    private WebApplicationContext wac;
    private WebBindingInitializer webBindingInitializer;

    private HttpMessageConverter messageConverter;


    public WebBindingInitializer getWebBindingInitializer() {
        return webBindingInitializer;
    }

    public void setWebBindingInitializer(WebBindingInitializer webBindingInitializer) {
        this.webBindingInitializer = webBindingInitializer;
    }

    public HttpMessageConverter getMessageConverter() {
        return messageConverter;
    }

    public void setMessageConverter(HttpMessageConverter messageConverter) {
        this.messageConverter = messageConverter;
    }

    public RequestMappingHandlerAdapter(){


    }
    public RequestMappingHandlerAdapter(WebApplicationContext wac){
        this.wac = wac;
        try {
            this.webBindingInitializer = (WebBindingInitializer) this.wac.getBean("webBindingInitializer");
            this.messageConverter = (HttpMessageConverter) this.wac.getBean("messageConverter");
         } catch (BeanException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return handleInternal(request, response, (HandlerMethod) handler);
    }

    public ModelAndView handleInternal(HttpServletRequest request, HttpServletResponse response,
                                       HandlerMethod handler) {
        ModelAndView mv = null;

        try {
            mv = invokeHandlerMethod(request, response, handler);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mv;

    }
    private ModelAndView invokeHandlerMethod(HttpServletRequest request, HttpServletResponse response, HandlerMethod handler) throws Exception {
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
        Object resultObj = null;
        Class<?> returnType = invocableMethod.getReturnType();
        try {
            resultObj = invocableMethod.invoke(handler.getBean(), methodParamObjs);
        }catch (Exception e){
            e.printStackTrace();
        }
        ModelAndView mav = null;
        if (invocableMethod.isAnnotationPresent(ResponseBody.class)){
            //ResponseBody
            this.messageConverter.write(resultObj, response);
        }
        else if (returnType == void.class) {

        }
        else {
            if (resultObj instanceof ModelAndView) {
                mav = (ModelAndView)resultObj;
            }
            else if(resultObj instanceof String) {
                String sTarget = (String)resultObj;
                mav = new ModelAndView();
                mav.setViewName(sTarget);
            }
        }
        return mav;
    }
}
