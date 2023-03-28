package com.minis.beans;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/3/27 23:50 
 */
public class PropertyValue {

    private final String type;
    private final String name;
    private final Object value;

    private final boolean isRef;

    public boolean isRef() {
        return isRef;
    }

    public PropertyValue(String type, String name, Object value, boolean isRef){
        this.type = type;
        this.value = value;
        this.name = name;
        this.isRef = isRef;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
