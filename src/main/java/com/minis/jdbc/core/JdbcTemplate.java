package com.minis.jdbc.core;

import com.minis.jdbc.PreparedStatementCallback;
import com.minis.jdbc.StatementCallback;

import javax.sql.DataSource;
import java.sql.*;

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
        try{
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(sql);
            for(int i = 0; i < args.length; i ++){
                Object arg = args[i];
                if(arg instanceof String) {
                    pstmt.setString(i+1, (String)arg);
                } else if (arg instanceof Integer){
                    pstmt.setInt(i+1,(int)arg);
                }
            }
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

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    protected abstract Object doInStatement(ResultSet rs);
}
