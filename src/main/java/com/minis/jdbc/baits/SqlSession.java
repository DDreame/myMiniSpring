package com.minis.jdbc.baits;

import com.minis.jdbc.PreparedStatementCallback;
import com.minis.jdbc.core.JdbcTemplate;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/6/5 17:03 
 */
public interface SqlSession  {
    void setJdbcTemplate(JdbcTemplate jdbcTemplate);
    void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory);
    Object selectOne(String sqlid, Object[] args, PreparedStatementCallback pstmtcallback);
}
