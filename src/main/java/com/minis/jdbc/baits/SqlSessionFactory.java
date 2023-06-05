package com.minis.jdbc.baits;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/6/5 16:38 
 */
public interface SqlSessionFactory {
    SqlSession openSession();
    MapperNode getMapperNode(String name);
}
