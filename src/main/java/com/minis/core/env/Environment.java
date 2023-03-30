package com.minis.core.env;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/3/30 20:07 
 */
public interface Environment {
    String[] getActiveProfiles();
    String[] getDefaultProfiles();
    boolean acceptsProfiles(String... profiles);
}
