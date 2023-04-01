package com.minis.context;

import com.minis.beans.BeanFactory;
import com.minis.beans.factory.DefaultListableBeanFactory;
import com.minis.beans.factory.config.BeanDefinition;
import com.minis.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import com.minis.beans.factory.support.BeanPostProcessor;
import com.minis.beans.factory.support.ConfigurableListableBeanFactory;
import com.minis.core.ClassPathXmlResource;
import com.minis.core.Resource;
import com.minis.beans.factory.BeanReader.XmlBeanDefinitionReader;
import com.minis.event.*;
import com.minis.exception.BeanException;

import java.util.List;

/***
 * @description : 一个 Bean 管理器 目前是单例的
 * @author : DDDreame
 * @date : 2023/3/21 00:46 
 */
public class ClassPathXmlApplicationContent extends AbstractApplicationContext {

    DefaultListableBeanFactory beanFactory;


    public ClassPathXmlApplicationContent(String fileName){
        this(fileName, true);
    }


    public ClassPathXmlApplicationContent(String fileName, boolean isRefresh){
        Resource resource = new ClassPathXmlResource(fileName);
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinition(resource);
        this.beanFactory = beanFactory;
        if (isRefresh) {
            try {
                refresh();
            } catch (BeanException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    void registerListeners() {
        ApplicationListener applicationListener = new ApplicationListener();
        this.getApplicationEventPublisher().addApplicationListener(applicationListener);
    }

    @Override
    void initApplicationEventPublisher() {
        ApplicationEventPublisher applicationEventPublisher = new SimpleApplicationEventPublisher();
        this.setApplicationEventPublisher(applicationEventPublisher);
    }

    @Override
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {

    }

    @Override
    void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        this.beanFactory.addBeanPostProcessor(new AutowiredAnnotationBeanPostProcessor(beanFactory));
    }


    @Override
    void onRefresh() {
        this.beanFactory.refresh();
    }

    @Override
    public DefaultListableBeanFactory getBeanFactory() {
        return this.beanFactory;
    }

    @Override
    public void addApplicationListener(ApplicationListener listener) {
        this.getApplicationEventPublisher().addApplicationListener(listener);
    }

    @Override
    void finishRefresh() {
        publishEvent(new ContextRefreshEvent("Context Refreshed ..."));
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        this.getApplicationEventPublisher().publishEvent(event);
    }
}
