package com.minis.aop;

import com.minis.aop.Interceptor.Advisor;
import com.minis.aop.Interceptor.MethodInterceptor;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/6/7 15:01 
 */
public class DefaultAdvisor implements Advisor {
    private MethodInterceptor methodInterceptor;


    @Override
    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    @Override
    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }
}
