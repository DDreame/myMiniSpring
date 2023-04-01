package com.minis.event;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/3/27 23:42 
 */
public interface ApplicationEventPublisher {

    void publishEvent(ApplicationEvent event);

    void addApplicationListener(ApplicationListener listener);
}
