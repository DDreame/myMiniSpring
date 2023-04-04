package com.minis.ioc.beans.factory.config;

/***
 * @description  : Todo
 * @author : DDDreame
 * {@code @date} : 2023/3/21 00:43
 */
public class BeanDefinition {
    /**
     * Bean 是否单例模式
     */
    String SCOPE_SINGLETON = "singleton";
    /**
     *
     */
    String SCOPE_PROTOTYPE = "prototype";
    /**
     * 是否延迟初始化
     */
    private boolean lazyInit = false;
    /**
     * 记录 Bean的依赖关系
     */
    private String[] dependsOn;
    /**
     * 构造器参数列表
     */
    private ArgumentValues argumentValues;
    /**
     * property 参数列表
     */
    private PropertyValues propertyValues;
    /**
     * 初始化方法
     */
    private String initMethodName;
    private volatile Object beanClass;
    private String scope = SCOPE_PROTOTYPE;


    private String id;
    private String className;
    public BeanDefinition(String id, String className){
        this.id = id;
        this.className = className;
    }

    public String getId() {
        return id;
    }

    public String getClassName() {
        return className;
    }

    public boolean isSingleton() {
        return SCOPE_SINGLETON.equals(scope);
    }

    public boolean isPrototype() {
        return SCOPE_PROTOTYPE.equals(scope);
    }

    public boolean isLazyInit() {
        return lazyInit;
    }

    public void setLazyInit(boolean lazyInit) {
        this.lazyInit = lazyInit;
    }

    public String[] getDependsOn() {
        return dependsOn;
    }

    public void setDependsOn(String[] dependsOn) {
        this.dependsOn = dependsOn;
    }

    public ArgumentValues getArgumentValues() {
        return argumentValues;
    }

    public void setArgumentValues(ArgumentValues argumentValues) {
        this.argumentValues = argumentValues;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

    public String getInitMethodName() {
        return initMethodName;
    }

    public void setInitMethodName(String initMethodName) {
        this.initMethodName = initMethodName;
    }

    public Object getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Object beanClass) {
        this.beanClass = beanClass;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
