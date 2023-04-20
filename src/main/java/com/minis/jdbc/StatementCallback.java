package com.minis.jdbc;

import java.sql.SQLException;
import java.sql.Statement;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/4/19 21:31 
 */
public interface StatementCallback {
    Object doInstatemetn(Statement stmt) throws SQLException;
}
