package com.minis.jdbc.core;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/4/20 20:55 
 */
public class ArgumentPreparedStatementSetter {
    private final Object[] args;
    public ArgumentPreparedStatementSetter(Object[] args){
        this.args = args;
    }

    public void setValues(PreparedStatement preparedStatement) throws SQLException{
        if(this.args != null){
            for(int i = 0; i < this.args.length; i ++){
                Object arg = args[i];
                doSetValue(preparedStatement, i + 1, arg);
            }
        }
    }

    protected void doSetValue(PreparedStatement preparedStatement, int parameterPosition, Object argValue) throws SQLException{
        Object arg = argValue;
        if (arg instanceof String){
            preparedStatement.setString(parameterPosition, (String) arg);
        }else if(arg instanceof Integer){
            preparedStatement.setInt(parameterPosition, (int)arg);
        }else if(arg instanceof  java.util.Date){
            preparedStatement.setDate(parameterPosition, new java.sql.Date(((java.util.Date)arg).getTime()));
        }
    }
}
