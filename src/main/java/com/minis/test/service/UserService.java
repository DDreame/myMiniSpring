package com.minis.test.service;

import com.minis.ioc.beans.factory.annotation.Autowired;
import com.minis.jdbc.core.JdbcTemplate;
import com.minis.jdbc.core.RowMapper;
import com.minis.test.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/4/19 20:56 
 */
public class UserService {

    public UserService(){

    }

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
                    ResultSet resultSet = preparedStatement.executeQuery();
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

    public List<User> getUserInfo3(int userId){
        String sql = "select id,name,birthday from user where id>?";
        return (List<User>) jdbcTemplate.query(sql, new Object[]{new Integer(userId)},
                (rs, rowNum) -> {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("name"));
                    user.setBirthday(new Date(rs.getDate("birthday").getTime()));
                    return user;
                });
    }
}
