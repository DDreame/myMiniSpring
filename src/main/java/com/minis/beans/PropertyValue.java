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

    public PropertyValue(String type, String name,Object value){
        this.type = type;
        this.value = value;
        this.name = name;
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
