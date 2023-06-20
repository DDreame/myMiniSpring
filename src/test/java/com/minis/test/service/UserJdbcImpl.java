package com.minis.test.service;

import com.minis.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.minis.test.entity.User;
/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/4/19 20:51 
 */
public class UserJdbcImpl extends JdbcTemplate {
    @Override
    protected Object doInStatement(ResultSet rs) {
        User user = null;
        try {
            if(rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setBirthday(new Date(rs.getDate("birthday").getTime()));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }
}
