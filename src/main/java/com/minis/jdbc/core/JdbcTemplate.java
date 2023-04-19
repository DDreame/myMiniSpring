package com.minis.jdbc.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/4/19 20:45 
 */
public abstract class JdbcTemplate {

    public JdbcTemplate(){

    }

    public Object query(String sql){
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        Object returnObj = null;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://159.75.122.81:3306/minispring?useUnicode=true&characterEncoding=utf-8";
            String username = "dddream";
            String password = "dddreame";
            con = DriverManager.getConnection(url, username, password);
            stmt = con.prepareStatement(sql);
            resultSet = stmt.executeQuery();
            returnObj = doInStatement(resultSet);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                resultSet.close();
                stmt.close();
                con.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return returnObj;
    }

    protected abstract Object doInStatement(ResultSet rs);
}
