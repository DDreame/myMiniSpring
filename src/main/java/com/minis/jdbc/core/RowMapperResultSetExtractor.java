package com.minis.jdbc.core;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/4/20 21:17 
 */
public class RowMapperResultSetExtractor<T> implements ResultetExtractor<List<T>> {

    private final RowMapper<T> rowMapper;

    public RowMapperResultSetExtractor(RowMapper<T> rowMapper){
        this.rowMapper = rowMapper;
    }
    @Override
    public List<T> extractData(ResultSet rs) throws SQLException {
        List<T> results = new ArrayList<>();
        int rowNum = 0;
        while(rs.next()){
            results.add(this.rowMapper.mapRow(rs, rowNum++));
        }
        return results;
    }
}
