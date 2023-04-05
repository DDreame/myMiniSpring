package com.minis.test;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/3/28 22:54 
 */
public class BaseBaseService {

    String name;
    private BaseService baseService;

    public BaseBaseService(String name){
        this.name = name;
        System.out.println("this is " + name);
    }
    public void sayHello(){
        System.out.println("Hello I'm " + name);
    }


    public BaseService getBaseService() {
        return baseService;
    }

    public void setBaseService(BaseService baseService) {
        this.baseService = baseService;
        System.out.println("I get Base service and it's name is " + baseService.name );
    }
}
