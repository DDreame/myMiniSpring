package com.minis.aop.methodMatcher;

import com.minis.aop.Interceptor.*;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/6/11 17:01 
 */
public class NameMatchMethodPointcutAdvisor implements PointerAdvisor{
    private Advice advice = null;
    private MethodInterceptor methodInterceptor;
    private String mappedName;
    private final NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
    public NameMatchMethodPointcutAdvisor(){
    }
    public Advice getAdvice() {
        return advice;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
        MethodInterceptor mi = null;
        if(advice instanceof BeforeAdvice){
            mi = new MethodBeforeAdviceInterceptor((MethodBeforeAdvice) advice);
        }
        else if(advice instanceof AfterAdvice){
            mi = new AfterReturningAdviceInterceptor((AfterReturningAdvice) advice);
        }
        else{
            mi = (MethodInterceptor) advice;
        }
        setMethodInterceptor(mi);
    }

    public String getMappedName() {
        return mappedName;
    }

    public void setMappedName(String mappedName) {
        this.mappedName = mappedName;
        this.pointcut.setMappedName(mappedName);
    }

    @Override
    public MethodInterceptor getMethodInterceptor() {
        return this.methodInterceptor;
    }

    @Override
    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    @Override
    public Pointcut getPointcut() {
        return this.pointcut;
    }

}
