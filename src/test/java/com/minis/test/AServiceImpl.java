package com.minis.test;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/3/26 13:37 
 */
public class AServiceImpl implements AService{

    private String name;

    private int level;

    private String property1;

    private int property2;

    public AServiceImpl(String name, Integer level){
        this.name = name;
        this.level = level;
        System.out.println("this name is " + name + ", and level is " + level);
    }


    public String getProperty1() {
        return property1;
    }

    public void setProperty1(String property1) {
        this.property1 = property1;
    }

    public int getProperty2() {
        return property2;
    }

    public void setProperty2(Integer property2) {
        this.property2 = property2;
    }

    @Override
    public void SayHello() {
        System.out.println("A Service "+property1 + " say Hello!");
    }
}
