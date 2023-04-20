package com.minis.jdbc.core;

import java.sql.ResultSet;
import java.sql.SQLException;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/4/20 21:14 
 */
public interface ResultetExtractor<T> {
    T extractData(ResultSet rs) throws SQLException;
}
