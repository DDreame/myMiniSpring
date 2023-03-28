package com.minis.test;

import com.minis.beans.factory.annotation.Autowired;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/3/28 17:45 
 */
public class BaseService {

    String name;

    public BaseService(String name){
        this.name = name;
        System.out.println("this is " + name);
    }
    @Autowired
    AServiceImpl aservice;

    public AServiceImpl getAservice() {
        return aservice;
    }

    public void sayHello(){
        System.out.println("Hello I'm " + name + " ");
        System.out.println("I have A aservice and it's getProperty1 is " + aservice.getProperty1() );
    }
    public void setAservice(AServiceImpl aservice) {
        this.aservice = aservice;
        System.out.println("I get A aservice and it's getProperty1 is " + aservice.getProperty1() );
    }
}
