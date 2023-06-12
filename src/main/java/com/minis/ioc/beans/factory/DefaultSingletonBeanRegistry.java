package com.minis.ioc.beans.factory;

import com.minis.ioc.beans.factory.support.SingletonBeanRegistry;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/3/27 22:41 
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    protected List<String> beanNames = new ArrayList<>();

    protected final Map<String, Object> singletons = new ConcurrentHashMap<>(256);

    protected final Map<String, Object> emptySingletons = new ConcurrentHashMap<>(16);
    protected final Map<String, Object> earlySingletons = new ConcurrentHashMap<>(16);


    protected final Map<String, Set<String>> dependentBeanMap = new ConcurrentHashMap<>(64);
    protected final Map<String, Set<String>> dependenciesForBeanMap = new ConcurrentHashMap<>(64);
    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        synchronized (this.singletons){
            beanNames.add(beanName);
            singletons.put(beanName,singletonObject);
            earlySingletons.remove(beanName);
        }
    }

    @Override
    public void registerEarlySingleton(String beanName, Object singletonObject) {
        synchronized (this.earlySingletons){
            beanNames.add(beanName);
            earlySingletons.put(beanName,singletonObject);
            emptySingletons.remove(beanName);
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

    public void registerDependentBean(String beanName, String dependentBeanName) {
        Set<String> dependentBeans = this.dependentBeanMap.get(beanName);
        if (dependentBeans != null && dependentBeans.contains(dependentBeanName)) {
            return;
        }

        // No entry yet -> fully synchronized manipulation of the dependentBeans Set
        synchronized (this.dependentBeanMap) {
            dependentBeans = this.dependentBeanMap.get(beanName);
            if (dependentBeans == null) {
                dependentBeans = new LinkedHashSet<String>(8);
                this.dependentBeanMap.put(beanName, dependentBeans);
            }
            dependentBeans.add(dependentBeanName);
        }
        synchronized (this.dependenciesForBeanMap) {
            Set<String> dependenciesForBean = this.dependenciesForBeanMap.get(dependentBeanName);
            if (dependenciesForBean == null) {
                dependenciesForBean = new LinkedHashSet<String>(8);
                this.dependenciesForBeanMap.put(dependentBeanName, dependenciesForBean);
            }
            dependenciesForBean.add(beanName);
        }

    }
}
