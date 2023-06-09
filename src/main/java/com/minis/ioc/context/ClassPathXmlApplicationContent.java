package com.minis.ioc.context;

import com.minis.ioc.beans.factory.DefaultListableBeanFactory;
import com.minis.ioc.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import com.minis.ioc.beans.factory.config.BeanDefinition;
import com.minis.ioc.beans.factory.support.ConfigurableListableBeanFactory;
import com.minis.ioc.core.ClassPathXmlResource;
import com.minis.ioc.core.Resource;
import com.minis.ioc.beans.factory.BeanReader.XmlBeanDefinitionReader;
import com.minis.ioc.event.*;
import com.minis.ioc.exception.BeanException;

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
                refresh();
            } catch (BeanException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void registerListeners() {
        ApplicationListener applicationListener = new ApplicationListener();
        this.getApplicationEventPublisher().addApplicationListener(applicationListener);
    }

    @Override
    protected void initApplicationEventPublisher() {
        ApplicationEventPublisher applicationEventPublisher = new SimpleApplicationEventPublisher();
        this.setApplicationEventPublisher(applicationEventPublisher);
    }

    @Override
    protected void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {

    }

    @Override
    protected void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        this.beanFactory.addBeanPostProcessor(new AutowiredAnnotationBeanPostProcessor(beanFactory));
    }


    @Override
    protected void onRefresh() {
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
    protected void finishRefresh() {
        publishEvent(new ContextRefreshEvent("Context Refreshed ..."));
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        this.getApplicationEventPublisher().publishEvent(event);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        return getBeanDefinition(beanName);
    }
}
