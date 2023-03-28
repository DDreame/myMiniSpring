package com.minis.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/3/27 22:41 
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry{

    protected List<String> beanNames = new ArrayList<>();

    protected final Map<String, Object> singletons = new ConcurrentHashMap<>(256);

    protected final Map<String, Object> earlySingletons = new ConcurrentHashMap<>();
    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        synchronized (this.singletons){
            beanNames.add(beanName);
            singletons.put(beanName,singletonObject);
        }
    }

    @Override
    public Object getSingleton(String beanName) {
        return singletons.get(beanName);
    }

    @Override
    public boolean containsSingleton(String beanName) {
        return singletons.containsKey(beanName);
    }

    @Override
    public String[] getSingletonNames() {
        return (String[]) this.beanNames.toArray();
    }

    protected void removeSingleton(String beanName){
        synchronized (this.singletons){
            this.beanNames.remove(beanName);
            this.singletons.remove(beanName);
        }
    }
}
