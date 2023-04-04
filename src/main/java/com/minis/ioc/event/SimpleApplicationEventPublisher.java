package com.minis.ioc.event;

import java.util.ArrayList;
import java.util.List;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/3/30 21:15 
 */
public class SimpleApplicationEventPublisher implements ApplicationEventPublisher{

    List<ApplicationListener> listenerList = new ArrayList<>();

    @Override
    public void publishEvent(ApplicationEvent event) {
        for(ApplicationListener listener: listenerList){
            listener.onApplicationEvent(event);
        }

    }

    @Override
    public void addApplicationListener(ApplicationListener listener) {
        this.listenerList.add(listener);
    }
}
