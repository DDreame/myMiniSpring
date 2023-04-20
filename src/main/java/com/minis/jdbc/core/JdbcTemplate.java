package com.minis.jdbc.core;

import com.minis.jdbc.PreparedStatementCallback;
import com.minis.jdbc.StatementCallback;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/4/19 20:45 
 */
public abstract class JdbcTemplate {



    DataSource dataSource;
    public JdbcTemplate(){

    }

    public Object query(StatementCallback stmtCallback){
        Connection con = null;
        Statement stmt = null;
        try{
            con = dataSource.getConnection();
            stmt = con.createStatement();
            return stmtCallback.doInstatemetn(stmt);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                stmt.close();
                con.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public Object query(String sql, Object[] args, PreparedStatementCallback preparedStatementCallback){
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(sql);
            ArgumentPreparedStatementSetter argumentPreparedStatementSetter = new ArgumentPreparedStatementSetter(args);
            argumentPreparedStatementSetter.setValues(pstmt);
            return preparedStatementCallback.doPreparedStatementCallback(pstmt);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                pstmt.close();
                con.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }
    public <T> List<T> query(String sql, Object[] args, RowMapper<T> rowMapper){
        RowMapperResultSetExtractor<T> resultSetExtractor = new RowMapperResultSetExtractor<>(rowMapper);
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(sql);
            ArgumentPreparedStatementSetter argumentPreparedStatementSetter = new ArgumentPreparedStatementSetter(args);
            argumentPreparedStatementSetter.setValues(pstmt);
            rs = pstmt.executeQuery();
            return resultSetExtractor.extractData(rs);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                pstmt.close();
                con.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    protected abstract Object doInStatement(ResultSet rs);
}
