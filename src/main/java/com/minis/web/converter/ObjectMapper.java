package com.minis.web.converter;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/4/7 00:45 
 */
public interface ObjectMapper {
    void setDateFormat(String dateFormat);
    void setDecimalFormat(String decimalFormat);
    String writeValuesAsString(Object obj);
}
