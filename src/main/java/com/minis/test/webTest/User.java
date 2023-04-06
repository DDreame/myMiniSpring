package com.minis.test.webTest;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/4/6 21:48 
 */
import java.util.Date;

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
