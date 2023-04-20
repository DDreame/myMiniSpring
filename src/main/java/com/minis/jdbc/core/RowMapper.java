package com.minis.jdbc.core;

import java.sql.ResultSet;
import java.sql.SQLException;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/4/20 21:09 
 */
public interface RowMapper<T> {


    T mapRow(ResultSet rs, int rowNum) throws SQLException;
}
