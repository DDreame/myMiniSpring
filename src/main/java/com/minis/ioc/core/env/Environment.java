package com.minis.ioc.core.env;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/3/30 20:07 
 */
public interface Environment extends PropertyResolver{
    String[] getActiveProfiles();
    String[] getDefaultProfiles();
    boolean acceptsProfiles(String... profiles);
}
