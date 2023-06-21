package com.minis.web;

import com.minis.ioc.beans.factory.DefaultListableBeanFactory;
import com.minis.ioc.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import com.minis.ioc.beans.factory.config.BeanDefinition;
import com.minis.ioc.beans.factory.support.BeanFactoryPostProcessor;
import com.minis.ioc.beans.factory.support.ConfigurableListableBeanFactory;
import com.minis.ioc.context.AbstractApplicationContext;
import com.minis.ioc.event.ApplicationEvent;
import com.minis.ioc.event.ApplicationEventPublisher;
import com.minis.ioc.event.ApplicationListener;
import com.minis.ioc.event.SimpleApplicationEventPublisher;
import com.minis.ioc.exception.BeanException;
import com.minis.utils.MyUtils;

import javax.servlet.ServletContext;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/4/5 15:36 
 */
public class AnnotationConfigWebApplicationContext extends AbstractApplicationContext implements WebApplicationContext {


    private WebApplicationContext parentApplicationContext;
    private ServletContext servletContext;

    DefaultListableBeanFactory beanFactory;

    private final List<BeanFactoryPostProcessor> beanFactoryPostProcessors = new ArrayList();

    public AnnotationConfigWebApplicationContext(String fileName) {
        this(fileName,null);
    }

    public AnnotationConfigWebApplicationContext(String fileName, WebApplicationContext applicationContext){
        this.parentApplicationContext = applicationContext;
        this.servletContext = this.parentApplicationContext.getServletContext();
        URL xmlPath = null;
        xmlPath = getServletContext().getClassLoader().getResource(fileName);
        List<String> packageNames = XmlScanComponentHelper.getNodeValue(xmlPath);
        List<String> controllerNames = scanPackages(packageNames);
        this.beanFactory = new DefaultListableBeanFactory();
        setParent(applicationContext);
        loadBeanDefinitions(controllerNames);
        if(true){
            try {
                refresh();
                refresh();
            } catch (BeanException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 此处如果修改为类名+首字母小写 方便自动加载
     * 但是 initController 则会出错
     * @param controllerNames
     */
    public void loadBeanDefinitions(List<String> controllerNames) {
        for (String controller : controllerNames) {

            BeanDefinition beanDefinition=new BeanDefinition(MyUtils.getClazzName(controller), controller);
            this.beanFactory.registerBeanDefinition(beanDefinition);
        }
    }

    private List<String> scanPackages(List<String> packages) {
        List<String> tempControllerNames = new ArrayList<>();
        for (String packageName : packages) {
            tempControllerNames.addAll(scanPackage(packageName));
        }
        return tempControllerNames;
    }

    private List<String> scanPackage(String packageName) {
        List<String> tempControllerNames = new ArrayList<>();
        URL url  =this.getClass().getClassLoader().getResource("/"+packageName.replaceAll("\\.", "/"));
        if(url == null){
            return tempControllerNames;
        }
        File dir = new File(url.getFile());
        for (File file : dir.listFiles()) {
            if(file.isDirectory()){
                scanPackage(packageName+"."+file.getName());
            }else if(file.getName().endsWith(".class")){
                String controllerName = packageName +"." +file.getName().replace(".class", "");
                tempControllerNames.add(controllerName);
            }
        }
        return tempControllerNames;
    }

    public void setParent(WebApplicationContext parentApplicationContext) {
        this.parentApplicationContext = parentApplicationContext;
        this.beanFactory.setParent(this.parentApplicationContext.getBeanFactory());
    }


    @Override
    public ServletContext getServletContext() {
        return this.servletContext;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Override
    protected void registerListeners() {
        ApplicationListener listener = new ApplicationListener();
        this.getApplicationEventPublisher().addApplicationListener(listener);
    }

    @Override
    protected void initApplicationEventPublisher() {
        ApplicationEventPublisher aep = new SimpleApplicationEventPublisher();
        this.setApplicationEventPublisher(aep);
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
    protected void finishRefresh() {

    }

    @Override
    public ConfigurableListableBeanFactory getBeanFactory() throws IllegalStateException {
        return this.beanFactory;
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        this.getApplicationEventPublisher().publishEvent(event);
    }

    @Override
    public void addApplicationListener(ApplicationListener listener) {
        this.getApplicationEventPublisher().addApplicationListener(listener);
    }

    public BeanDefinition getBeanDefinition(String beanName){
        return this.beanFactory.getBeanDefinition(beanName);
    }
}
