package com.minis.ioc.beans.factory.support;

import com.minis.ioc.beans.factory.config.*;
import com.minis.ioc.beans.factory.DefaultSingletonBeanRegistry;
import com.minis.ioc.exception.BeanException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/3/28 23:32 
 */
public abstract class AbstractFactory extends DefaultSingletonBeanRegistry
        implements ConfigurableBeanFactory, BeanDefinitionRegistry {

    public Map<String, BeanDefinition> beanDefinitions = new ConcurrentHashMap<>(256);
    private List<String> beanDefinitionNames = new ArrayList<>();

    public void refresh() {
        for (String beanName : beanDefinitionNames) {
            try { getBean(beanName);
            } catch (BeanException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Object getBean(String beanName) throws BeanException {
        Object singleton = singletons.get(beanName);
        if(singleton == null){
            singleton = earlySingletons.get(beanName);
            if(singleton == null){
                BeanDefinition beanDefinition = beanDefinitions.get(beanName);
                if(beanDefinition == null){
                    throw new BeanException("No BeanNama:" + beanName);
                }
                try{
                    singleton = createBean(beanDefinition);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                this.registerSingleton(beanName,singleton);
                // 预留beanpostprocessor位置
                // step 1: postProcessBeforeInitialization
                applyBeanPostProcessorsBeforeInitialization(singleton, beanName);
                // step 2: afterPropertiesSet
                // step 3: init-method
                if (beanDefinition.getInitMethodName() != null && !"".equals(beanDefinition.getInitMethodName())) {
                    invokeInitMethod(beanDefinition, singleton);
                }
                // step 4: postProcessAfterInitialization
                applyBeanPostProcessorsAfterInitialization(singleton, beanName);
            }


        }
        return singleton;
    }

    private void invokeInitMethod(BeanDefinition beanDefinition, Object obj){
        Class<?> clazz = beanDefinition.getClass();
        Method method = null;
        try{
            method = clazz.getMethod(beanDefinition.getClassName());
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        try {
            method.invoke(obj);
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private Object createBean(BeanDefinition beanDefinition){

        Class<?> clazz = null;
        Object obj = doCreateBean(beanDefinition);
        earlySingletons.put(beanDefinition.getId(), obj);
        Constructor<?> constructor = null;
        try{
            clazz = Class.forName(beanDefinition.getClassName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        //处理属性
        handleProperties(beanDefinition, clazz, obj);
        return obj;
    }

    private Object doCreateBean(BeanDefinition beanDefinition){
        Class<?> clazz = null;
        Object obj = null;
        Constructor<?> constructor = null;
        try{
            clazz = Class.forName(beanDefinition.getClassName());
            ArgumentValues avs = beanDefinition.getArgumentValues();
            if(!avs.isEmpty()){
                Class<?>[] paramTypes = new Class<?>[avs.getArgumentCount()];
                Object[] paramValues = new Object[avs.getArgumentCount()];
                for(int i = 0; i < avs.getArgumentCount(); i ++){
                    ArgumentValue argumentValue = avs.getIndexedArgumentValue(i);
                    if ("String".equals(argumentValue.getType()) || "java.lang.String".equals(argumentValue.getType())) {
                        paramTypes[i] = String.class;
                        paramValues[i] = argumentValue.getValue();
                    } else if ("Integer".equals(argumentValue.getType()) || "java.lang.Integer".equals(argumentValue.getType())) {
                        paramTypes[i] = Integer.class;
                        paramValues[i] = Integer.valueOf((String)argumentValue.getValue());
                    } else if ("int".equals(argumentValue.getType())) {
                        paramTypes[i] = int.class;
                        paramValues[i] = Integer.valueOf((String)argumentValue.getValue());
                    } else {
                        paramTypes[i] = String.class;
                        paramValues[i] = argumentValue.getValue();
                    }
                }
                constructor = clazz.getConstructor(paramTypes);
                obj = constructor.newInstance(paramValues);
            }else {
                obj = clazz.newInstance();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return obj;
    }

    private void handleProperties(BeanDefinition beanDefinition, Class<?> clazz, Object obj){
        PropertyValues pvs = beanDefinition.getPropertyValues();
        if(!pvs.isEmpty()){
            for(int i = 0; i < pvs.size(); i ++){
                PropertyValue pv = pvs.getPropertyValueList().get(i);
                String pType = pv.getType();
                String pName = pv.getName();
                Object pValue = pv.getValue();
                boolean isRef = pv.isRef();
                Class<?>[] paramTypes = new Class<?>[1];

                Object[] params = new Object[1];
                if(!isRef){
                    if ("String".equals(pType) || "java.lang.String".equals(pType)) {
                        paramTypes[0] = String.class;
                        params[0] = pValue;
                    }
                    else if ("Integer".equals(pType) || "java.lang.Integer".equals(pType)) {
                        paramTypes[0] = Integer.class;
                        params[0] = Integer.valueOf(String.valueOf(pValue));
                    }  else if ("int".equals(pType)) {
                        paramTypes[0] = int.class;
                        params[0] = Integer.valueOf(String.valueOf(pValue));
                    } else {
                        // 默认为string
                        paramTypes[0] = String.class;
                        params[0] = pValue;
                    }
                }else {
                    try {
                        paramTypes[0] = Class.forName(pType);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        params[0] = getBean((String)pValue);
                    } catch (BeanException e) {
                        throw new RuntimeException(e);
                    }
                }
                String methodName = "set" + pName.substring(0, 1).toUpperCase() + pName.substring(1);
                Method method = null;
                try{
                    assert clazz != null;
                    method = clazz.getMethod(methodName, paramTypes);
                    method.invoke(obj,params);
                }catch (Exception e){
                    System.out.println(methodName + " ！！！！");
                    e.printStackTrace();
                }

            }

        }
    }

    @Override
    public Boolean containsBean(String beanName) {
        return beanDefinitions.containsKey(beanName);
    }

    @Override
    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        beanDefinitions.put(beanDefinition.getId(),beanDefinition);
        beanDefinitionNames.add(beanDefinition.getId());
    }

    @Override
    public boolean isSingleton(String beanName) {
        return this.beanDefinitions.get(beanName).isSingleton();
    }

    @Override
    public boolean isPrototype(String beanName) {
        return this.beanDefinitions.get(beanName).isPrototype();
    }

    @Override
    public Class<?> getType(String name) {
        return this.beanDefinitions.get(name).getClass();
    }

    @Override
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        this.beanDefinitionNames.add(name);
        this.beanDefinitions.put(name,beanDefinition);
        if(!beanDefinition.isLazyInit()){
            try {
                getBean(name);
            }catch (BeanException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void removeBeanDefinition(String name) {
        this.beanDefinitionNames.remove(name);
        this.beanDefinitions.remove(name);
        this.removeSingleton(name);
    }

    @Override
    public BeanDefinition getBeanDefinition(String name) {
        return this.beanDefinitions.get(name);
    }

    @Override
    public boolean containsBeanDefinition(String name) {
        return this.beanDefinitions.containsKey(name);
    }

    abstract public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeanException;

    abstract public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws  BeanException;
}
