package com.minis.ioc.beans;

import com.minis.ioc.beans.CustomNumberEditor;
import com.minis.ioc.beans.PropertyEditor;
import com.minis.ioc.beans.StringEditor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/4/6 17:03 
 */
public class PropertyEditorRegistrySupport {
    private Map<Class<?>, PropertyEditor> defaultEditors;

    private Map<Class<?>, PropertyEditor> customEditors;

    protected void registerDefaultEditors() { createDefaultEditors(); }
    public PropertyEditor getDefaultEditors(Class<?> requiredType){
        return this.defaultEditors.get(requiredType);
    }

    private void createDefaultEditors(){
        this.defaultEditors = new HashMap<>(64);
        this.defaultEditors.put(int.class, new CustomNumberEditor(Integer.class, false));
        this.defaultEditors.put(Integer.class, new CustomNumberEditor(Integer.class, true));
        this.defaultEditors.put(long.class, new CustomNumberEditor(Long.class, false));
        this.defaultEditors.put(Long.class, new CustomNumberEditor(Long.class, true));
        this.defaultEditors.put(float.class, new CustomNumberEditor(Float.class, false));
        this.defaultEditors.put(Float.class, new CustomNumberEditor(Float.class, true));
        this.defaultEditors.put(double.class, new CustomNumberEditor(Double.class, false));
        this.defaultEditors.put(Double.class, new CustomNumberEditor(Double.class, true));
        this.defaultEditors.put(BigDecimal.class, new CustomNumberEditor(BigDecimal.class, true));
        this.defaultEditors.put(BigInteger.class, new CustomNumberEditor(BigInteger.class, true));
        this.defaultEditors.put(String.class, new StringEditor(String.class, true));
    }

    public void registerCustomEditor(Class<?> requiredType, PropertyEditor propertyEditor){
        if(this.customEditors == null){
            this.customEditors = new LinkedHashMap<>(16);
        }
        this.customEditors.put(requiredType, propertyEditor);
    }

    public PropertyEditor findCustomEditor(Class<?> requiredType){
        return getCustomEditor(requiredType);
    }

    public boolean hasCustomEditorForElement(Class<?> elementType){
        return (elementType != null && this.customEditors != null && this.customEditors.containsKey(elementType));
    }
    protected PropertyEditor getCustomEditor(Class<?> requiredType){
        if(requiredType == null || this.customEditors == null){
            return null;
        }
        return this.customEditors.get(requiredType);
    }


    protected PropertyEditor getDefaultEditor(Class<?> propertyClz) {
        return this.defaultEditors.get(propertyClz);
    }
}
