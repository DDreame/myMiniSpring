package com.minis.test.aop;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/6/6 23:43 
 */
public class Action1 implements IAction {

    @Override
    public void doAction() {
        System.out.println("really do action");
    }

    @Override
    public void doSome() {
        System.out.println("this is action1 do some");
    }
}
