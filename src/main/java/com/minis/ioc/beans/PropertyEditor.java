package com.minis.ioc.beans;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/4/6 16:36 
 */
public interface PropertyEditor {

    void setAsText(String text);

    void setValue(Object value);

    Object getValue();

    String getAsText();
}
