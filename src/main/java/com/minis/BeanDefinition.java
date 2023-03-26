package com.minis;

/***
 * @description  : Todo
 * @author : 梦某人
 * @date : 2023/3/21 00:43
 */
public class BeanDefinition {
    private String id;
    private String className;

    public BeanDefinition(String id, String className){
        this.id = id;
        this.className = className;
    }

    public String getId() {
        return id;
    }

    public String getClassName() {
        return className;
    }

}
