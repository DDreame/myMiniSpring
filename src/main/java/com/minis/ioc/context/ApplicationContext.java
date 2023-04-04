package com.minis.ioc.context;

import com.minis.ioc.beans.factory.support.BeanFactoryPostProcessor;
import com.minis.ioc.beans.factory.support.ConfigurableBeanFactory;
import com.minis.ioc.beans.factory.support.ConfigurableListableBeanFactory;
import com.minis.ioc.beans.factory.support.ListableBeanFactory;
import com.minis.ioc.core.env.Environment;
import com.minis.ioc.core.env.EnvironmentCapable;
import com.minis.ioc.event.ApplicationEventPublisher;
import com.minis.ioc.exception.BeanException;

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
