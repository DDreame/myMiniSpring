package com.minis.beans.factory.support;

import com.minis.beans.BeanFactory;
import com.minis.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import com.minis.exception.BeanException;

import java.util.ArrayList;
import java.util.List;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/3/28 23:30 
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractFactory implements AutowireCapableBeanFactory {

    private final List<BeanPostProcessor> processorList = new ArrayList<>();


    public void addBeanPostProcessor(BeanPostProcessor processor){
        this.processorList.remove(processor);
        this.processorList.add(processor);
    }


    public int getProcessorCount(){
        return this.processorList.size();
    }

    public List<BeanPostProcessor> getProcessorList(){
        return this.processorList;
    }



    public int getBeanPostProcessorCount() {
        return this.processorList.size();
    }
    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.processorList;
    }


    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeanException {
        Object result = existingBean;
        for(BeanPostProcessor processor : processorList){
            existingBean = processor.postProcessorBeforeInitialization(existingBean, beanName);
            if(existingBean == null){
                return result;
            }
        }

        return result;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeanException {
        Object result = existingBean;
        for(BeanPostProcessor processor : processorList){
            result = processor.postProcessAfterInitialization(result, beanName);
            if(result == null){
                return result;
            }
        }

        return result;
    }
}
