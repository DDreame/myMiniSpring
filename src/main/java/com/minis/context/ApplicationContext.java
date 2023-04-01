package com.minis.context;

import com.minis.beans.factory.support.BeanFactoryPostProcessor;
import com.minis.beans.factory.support.ConfigurableBeanFactory;
import com.minis.beans.factory.support.ConfigurableListableBeanFactory;
import com.minis.beans.factory.support.ListableBeanFactory;
import com.minis.core.env.Environment;
import com.minis.core.env.EnvironmentCapable;
import com.minis.event.ApplicationEventPublisher;
import com.minis.event.ApplicationListener;
import com.minis.exception.BeanException;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/3/30 21:17 
 */
public interface ApplicationContext extends EnvironmentCapable, ListableBeanFactory, ConfigurableBeanFactory, ApplicationEventPublisher {

    String getApplicationName();
    long getStartupDate();
    ConfigurableListableBeanFactory getBeanFactory() throws IllegalStateException;
    void setEnvironment(Environment environment);
    Environment getEnvironment();
    void addBeanFactoryPostProcessor(BeanFactoryPostProcessor postProcessor);
    void refresh() throws BeanException, IllegalStateException;
    void close();
    boolean isActive();
}
