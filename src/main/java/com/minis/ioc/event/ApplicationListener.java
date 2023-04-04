package com.minis.ioc.event;

import java.util.EventListener;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/3/30 21:12 
 */
public class ApplicationListener implements EventListener {

    void onApplicationEvent(ApplicationEvent event){
        System.out.println(event.toString());
    }
}
