package com.minis.test.aop;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/6/8 17:26 
 */
public class Action2 implements IAction{
    @Override
    public void doAction() {
        System.out.println("this is action2");
    }

    @Override
    public void doSome() {
        System.out.println("this is action2 do some!");
    }
}
