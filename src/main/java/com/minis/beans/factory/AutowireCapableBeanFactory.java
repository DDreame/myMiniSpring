package com.minis.beans.factory;

import com.minis.beans.factory.support.AutowiredAnnotationBeanPostProcessor;
import com.minis.exception.BeanException;

import java.util.ArrayList;
import java.util.List;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/3/28 23:30 
 */
public class AutowireCapableBeanFactory extends AbstractFactory {

    private List<AutowiredAnnotationBeanPostProcessor> processorList = new ArrayList<>();

    public void addBeanPostProcessor(AutowiredAnnotationBeanPostProcessor processor){
        this.processorList.remove(processor);
        this.processorList.add(processor);
    }

    public int getProcessorCount(){
        return this.processorList.size();
    }

    public List<AutowiredAnnotationBeanPostProcessor> getProcessorList(){
        return this.processorList;
    }



    @Override
    public Object applyBeanPostProcessorBeforeInitialization(Object existingBean, String beanName) throws BeanException {
        Object result = existingBean;
        for(AutowiredAnnotationBeanPostProcessor processor : processorList){
            processor.setBeanFactory(this);
            existingBean = processor.postProcessorBeforeInitialization(existingBean, beanName);
            if(existingBean == null){
                return result;
            }
        }

        return result;
    }

    @Override
    public Object applyBeanPostProcessorAfterInitialization(Object existingBean, String beanName) throws BeanException {
        Object result = existingBean;
        for(AutowiredAnnotationBeanPostProcessor processor : processorList){
            processor.setBeanFactory(this);
            result = processor.postProcessAfterInitialization(result, beanName);
            if(result == null){
                return result;
            }
        }

        return result;
    }
}
