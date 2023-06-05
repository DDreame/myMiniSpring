package com.minis.jdbc.baits;

import com.minis.jdbc.PreparedStatementCallback;
import com.minis.jdbc.core.JdbcTemplate;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/6/5 17:03 
 */
public class DefaultSqlSession implements SqlSession{
    JdbcTemplate jdbcTemplate;
    @Override
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    SqlSessionFactory sqlSessionFactory;
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public Object selectOne(String sqlid, Object[] args, PreparedStatementCallback pstmtcallback) {
        String sql = this.sqlSessionFactory.getMapperNode(sqlid).getSql();
        return jdbcTemplate.query(sql, args, pstmtcallback);
    }

    private void buildParameter(){ }
    private Object resultSet2Obj() { return null; }
}
