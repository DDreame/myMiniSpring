package com.minis.web.converter;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/4/7 00:46 
 */
public class DefaultObjectMapper implements ObjectMapper{
    String dateFormat = "yyyy-MM-dd";
    DateTimeFormatter datetimeFormatter = DateTimeFormatter.ofPattern(dateFormat);
    String decimalFormat = "#,##0.00"; DecimalFormat decimalFormatter = new DecimalFormat(decimalFormat);
    public DefaultObjectMapper() {

    }
    @Override
    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
        this.datetimeFormatter = DateTimeFormatter.ofPattern(dateFormat);
    }

    @Override
    public void setDecimalFormat(String decimalFormat) {
        this.decimalFormat = decimalFormat;
        this.decimalFormatter = new DecimalFormat(decimalFormat);
    }

    @Override
    public String writeValuesAsString(Object obj) {
        if (obj instanceof List) {
            return writeListValuesAsString((List<?>) obj);
        } else {
            return writeSingleValueAsString(obj);
        }
    }

    private String writeListValuesAsString(List<?> list) {
        StringBuilder sJsonStr = new StringBuilder("[");
        for (Object obj : list) {
            if (sJsonStr.length() > 1) {
                sJsonStr.append(',');
            }
            sJsonStr.append(writeSingleValueAsString(obj));
        }
        sJsonStr.append("]");
        return sJsonStr.toString();
    }

    private String writeSingleValueAsString(Object obj) {
        StringBuilder sJsonStr = new StringBuilder("{");
        Class<?> clz = obj.getClass();
        Field[] fields = clz.getDeclaredFields();
        //对返回对象中的每一个属性进行格式转换
        for (Field field : fields) {
            String sField = "";
            Object value = null;
            Class<?> type = null;
            String name = field.getName();
            String strValue = "";
            field.setAccessible(true);
            try {
                value = field.get(obj);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            type = field.getType();

            if (value instanceof Date) {
                LocalDate localDate = ((Date)value).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                strValue = localDate.format(this.datetimeFormatter);
            } else if (value instanceof BigDecimal || value instanceof Double || value instanceof Float){
                strValue = this.decimalFormatter.format(value);
            } else if(value != null){
                strValue = value.toString();
            }else {
                strValue = "null";
            }
            //拼接Json串
            if (sJsonStr.toString().equals("{")) {
                sField = "\"" + name + "\":\"" + strValue + "\"";
            } else {
                sField = ",\"" + name + "\":\"" + strValue + "\"";
            }
            sJsonStr.append(sField);
        }
        sJsonStr.append("}");
        return sJsonStr.toString();
    }
}
