package com.minis.ioc.beans.factory;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/6/7 10:23 
 */
public interface FactoryBean<T> {
    T getObject() throws Exception;
    Class<?> getObjectType();
    default boolean isSingleton(){
        return true;
    }
}
