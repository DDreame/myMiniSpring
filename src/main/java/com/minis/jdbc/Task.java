package com.minis.jdbc;


/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/4/19 21:24 
 */
public class Task {
    public void executeWithCallback(Callback callback){
        //测试时打开下方
        //System.out.println("Execute");

        //具体的业务逻辑
        //execute();
        if(callback != null) callback.call();
    }

    public static void main(String[] args) {
        Task task = new Task();
        Callback callback = new Callback() {
            @Override
            public void call() {
                System.out.println("Call Back....");
            }
        };
        task.executeWithCallback(callback);
    }
}


