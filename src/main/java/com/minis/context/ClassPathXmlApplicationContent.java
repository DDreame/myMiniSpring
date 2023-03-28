package com.minis.context;

import com.minis.beans.BeanFactory;
import com.minis.beans.factory.AutowireCapableBeanFactory;
import com.minis.beans.factory.config.BeanDefinition;
import com.minis.beans.factory.support.AutowiredAnnotationBeanPostProcessor;
import com.minis.core.ClassPathXmlResource;
import com.minis.core.Resource;
import com.minis.beans.factory.SimpleBeanFactory;
import com.minis.beans.factory.BeanReader.XmlBeanDefinitionReader;
import com.minis.exception.BeanException;

import java.util.List;

/***
 * @description : 一个 Bean 管理器 目前是单例的
 * @author : DDDreame
 * @date : 2023/3/21 00:46 
 */
public class ClassPathXmlApplicationContent implements BeanFactory {

    AutowireCapableBeanFactory beanFactory;


    public ClassPathXmlApplicationContent(String fileName){
        this(fileName, true);
    }


    public ClassPathXmlApplicationContent(String fileName, boolean isRefresh){
        Resource resource = new ClassPathXmlResource(fileName);
        AutowireCapableBeanFactory beanFactory = new AutowireCapableBeanFactory();
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
    public List<AutowiredAnnotationBeanPostProcessor> getBeanFactoryPostProcessors() {
        return this.beanFactory.getProcessorList();
    }

    public void refresh() throws BeanException, IllegalStateException {
        // 注册处理器.
        registerBeanPostProcessors(this.beanFactory);
        onRefresh();
    }


    private void registerBeanPostProcessors(AutowireCapableBeanFactory beanFactory) {
        beanFactory.addBeanPostProcessor(new AutowiredAnnotationBeanPostProcessor());
    }

    private void onRefresh() {
        this.beanFactory.refresh();
    }



    public Object getBean(String beanName) throws BeanException {
        return this.beanFactory.getBean(beanName);
    }

    public Boolean containsBean(String beanName){
        return this.beanFactory.containsBean(beanName);
    }

    public void registerBeanDefinition(BeanDefinition beanDefinition){
        this.beanFactory.registerBeanDefinition(beanDefinition);
    }

    @Override
    public boolean isSingleton(String beanName) {
        return this.beanFactory.isSingleton(beanName);
    }

    @Override
    public boolean isPrototype(String beanName) {
        return this.beanFactory.isPrototype(beanName);
    }

    @Override
    public Class<?> getType(String name) {
        return this.beanFactory.getType(name);
    }


}
