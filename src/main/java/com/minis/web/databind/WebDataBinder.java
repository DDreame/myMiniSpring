package com.minis.web.databind;

import com.minis.ioc.beans.AbstractPropertyAccessor;
import com.minis.ioc.beans.PropertyEditor;
import com.minis.ioc.beans.factory.config.PropertyValues;
import com.minis.utils.WebUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/4/6 16:30 
 */
public class WebDataBinder {
    private Object target;

    private Class<?> clz;

    private String objectName;

    AbstractPropertyAccessor propertyAccessor;

    public WebDataBinder(Object target){
        this(target, "");
    }

    public WebDataBinder(Object target, String targetName){
        this.target = target;
        this.objectName = targetName;
        this.clz = this.target.getClass();
        this.propertyAccessor = new BeanWrapperImpl(target);
    }

    public void bind(HttpServletRequest request){
        PropertyValues mpvs = assignParameters(request);
        addBindValues(mpvs, request);
        doBind(mpvs);
    }

    private void addBindValues(PropertyValues mpvs, HttpServletRequest request) {
    }

    private void doBind(PropertyValues propertyValues){
        applyPropertyValues(propertyValues);
    }


    public AbstractPropertyAccessor getPropertyAccessor() {
        return this.propertyAccessor;
    }

    protected void applyPropertyValues(PropertyValues mpvs){
        getPropertyAccessor().setPropertyValues(mpvs);
    }
    private PropertyValues assignParameters(HttpServletRequest request){
        Map<String, Object> map = WebUtils.getParametersStartingWith(request, "");
        return new PropertyValues(map);
    }

    public void registerCustomEditor(Class<?> requiredType, PropertyEditor propertyEditor) {
        getPropertyAccessor().registerCustomEditor(requiredType, propertyEditor);
    }

}
