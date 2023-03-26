package com.minis.exception;

/***
 * @description : Bean异常类 逐步扩展版
 * @author : DDDreame
 * @date : 2023/3/26 14:19 
 */
public class BeanException extends Exception{

    /**
     * 第一版本，直接调用父类
     * @param msg 错误信息
     */
    public BeanException(String msg){
        super(msg);
    }
}
