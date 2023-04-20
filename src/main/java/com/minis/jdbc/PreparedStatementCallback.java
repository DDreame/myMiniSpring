package com.minis.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/4/20 10:33 
 */
public interface PreparedStatementCallback {
    Object doPreparedStatementCallback(PreparedStatement preparedStatement) throws SQLException;
}
