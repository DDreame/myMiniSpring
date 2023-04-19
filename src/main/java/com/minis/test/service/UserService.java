package com.minis.test.service;

import com.minis.jdbc.core.JdbcTemplate;
import com.minis.test.entity.User;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/4/19 20:56 
 */
public class UserService {

    public User getUserInfo(int userId){
        String sql = "select id,name,birthday from user where id=" + userId;
        JdbcTemplate jdbcTemplate = new UserJdbcImpl();
        return (User) jdbcTemplate.query(sql);
    }
}
