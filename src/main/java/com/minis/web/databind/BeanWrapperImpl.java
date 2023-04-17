package com.minis.web.databind;

import com.minis.ioc.beans.AbstractPropertyAccessor;
import com.minis.ioc.beans.PropertyEditor;
import com.minis.ioc.beans.PropertyEditorRegistrySupport;
import com.minis.ioc.beans.factory.config.PropertyValue;
import com.minis.ioc.beans.factory.config.PropertyValues;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/4/6 19:16 
 */
public class BeanWrapperImpl extends AbstractPropertyAccessor {
    Object wrappedObject;
    Class<?> clazz;

    public BeanWrapperImpl(Object object){
        super();
        registerDefaultEditors();
        this.wrappedObject = object;
        this.clazz = object.getClass();
    }

    public void setBeanInstance(Object object){
        this.wrappedObject = object;
    }

    public Object getBeanInstance(){
        return wrappedObject;
    }

    public void setPropertyValue(PropertyValue pv){
        BeanPropertyHandler propertyHandler = new BeanPropertyHandler(pv.getName());
        PropertyEditor pe = this.getCustomEditor(propertyHandler.getPropertyClz());
        if (pe == null) {
            pe = this.getDefaultEditor(propertyHandler.getPropertyClz());

        }
        if(pe != null){
            pe.setAsText((String) pv.getValue());
            propertyHandler.setValue(pe.getValue());
        }
        else {
            propertyHandler.setValue(pv.getValue());
        }
    }

    class BeanPropertyHandler{
        Method writeMethod = null;
        Method readMethod = null;
        Class<?> propertyClz = null;
        public Class<?> getPropertyClz(){
            return propertyClz;
        }
        public BeanPropertyHandler(String propertyName){
            try{
                Field field = clazz.getDeclaredField(propertyName);
                propertyClz = field.getType();
                this.writeMethod = clazz.getDeclaredMethod("set"+
                        propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1), propertyClz);
                this.readMethod = clazz.getDeclaredMethod("get"+
                        propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1), propertyClz);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        public Object getValue(){
            Object result = null;
            writeMethod.setAccessible(true);
            try{
                result = readMethod.invoke(wrappedObject);
            }catch (Exception e){
                e.printStackTrace();
            }
            return result;
        }
        public void setValue(Object value){
            writeMethod.setAccessible(true);
            try {
                writeMethod.invoke(wrappedObject, value);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
