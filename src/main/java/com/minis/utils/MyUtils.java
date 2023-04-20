package com.minis.utils;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/4/20 15:56 
 */
public abstract class MyUtils {

    public static String getClazzName(String calzzName){
        String beanName = calzzName.substring(calzzName.lastIndexOf(".") + 1);
        return Character.toLowerCase(beanName.charAt(0)) + beanName.substring(1);
    }
}
