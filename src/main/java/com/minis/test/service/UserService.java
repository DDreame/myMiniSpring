package com.minis.test.service;

import com.minis.ioc.beans.factory.annotation.Autowired;
import com.minis.jdbc.core.JdbcTemplate;
import com.minis.test.entity.User;

import java.sql.ResultSet;
import java.util.Date;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/4/19 20:56 
 */
public class UserService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public User getUserInfo(int userId){
        String sql = "select id,name,birthday from user where id=" + userId;
        return (User) jdbcTemplate.query(
                (stmt -> {
                    ResultSet resultSet = stmt.executeQuery(sql);
                    User user = null;
                    if(resultSet.next()){
                        user = new User();
                        user.setId(resultSet.getInt("id"));
                        user.setName(resultSet.getString("name"));
                        user.setBirthday(new Date(resultSet.getDate("birthday").getTime()));

                    }
                    return user;
                })
        );
    }

    public User getUserInfo2(int userId){
        String sql = "select id,name,birthday from user where id=?";
        return (User) jdbcTemplate.query(sql, new Object[]{new Integer(userId)},
                (preparedStatement -> {
                    ResultSet resultSet = preparedStatement.executeQuery(sql);
                    User user = null;
                    if(resultSet.next()){
                        user = new User();
                        user.setId(resultSet.getInt("id"));
                        user.setName(resultSet.getString("name"));
                        user.setBirthday(new Date(resultSet.getDate("birthday").getTime()));

                    }
                    return user;
                })
        );
    }
}
