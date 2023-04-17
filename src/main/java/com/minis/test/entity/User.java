package com.minis.test.entity;

import java.util.Date;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/4/17 22:06 
 */
public class User {
    int id = 1;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    String name = "";

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    Date birthday = new Date();

    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

}
