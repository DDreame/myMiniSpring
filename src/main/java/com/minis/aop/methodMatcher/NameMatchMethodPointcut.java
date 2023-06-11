package com.minis.aop.methodMatcher;

import com.minis.utils.PatternMatchUtils;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/6/11 16:49 
 */
public class NameMatchMethodPointcut implements MethodMatcher, Pointcut{
    private String mappedName = "";

    public void setMappedName(String mappedName){
        this.mappedName = mappedName;
    }

    @Override
    public boolean matcher(Method method, Class<?> targetClass) {
        if(mappedName.equals(method.getName()) || isMatch(method.getName(), mappedName)){
            return true;
        }
        return false;
    }
    protected boolean isMatch(String methodName, String mappedName){
        return PatternMatchUtils.simpleMatch(mappedName, methodName);
    }
    @Override
    public MethodMatcher getMethodMatcher() {
        return this;
    }
}
