package com.minis.web.servlet;

import com.minis.web.RequestMapping;
import com.minis.web.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/4/5 20:14 
 */
public class RequestMappingHandlerMapping implements HandlerMapping{

    WebApplicationContext wac;
    private final MappingRegistry mappingRegistry = new MappingRegistry();

    public RequestMappingHandlerMapping(WebApplicationContext wac){
        this.wac = wac;
        initMapping();
    }

    protected void initMapping(){
        Class<?> clazz = null;
        Object obj = null;
        String[] controllerNames = wac.getBeanDefinitionNames();
        for(String controllerName: controllerNames){
            try {
                clazz = Class.forName(controllerName);
                obj = clazz.newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            Method[] methods = clazz.getDeclaredMethods();
            for(Method method: methods){
                boolean isRequestMapping = method.isAnnotationPresent(RequestMapping.class);
                if(isRequestMapping){
                    String name = method.getName();
                    String urlMapping = method.getAnnotation(RequestMapping.class).value();
                    this.mappingRegistry.getUrlMappingNames().add(urlMapping);
                    this.mappingRegistry.getMappingObjs().put(urlMapping,obj);
                    this.mappingRegistry.getMappingMethods().put(urlMapping, method);
                }
            }

        }
    }

    @Override
    public HandlerMethod getHandler(HttpServletRequest request) throws Exception {
        String sPath = request.getServletPath();
        if(!this.mappingRegistry.getUrlMappingNames().contains(sPath)){
            System.out.println(sPath + " is not contains!");
            return null;
        }
        Method method = this.mappingRegistry.getMappingMethods().get(sPath);
        Object obj = this.mappingRegistry.getMappingObjs().get(sPath);
        return new HandlerMethod(method, obj);
    }
}
