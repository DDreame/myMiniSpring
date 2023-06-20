package com.minis.test;

import com.minis.ioc.beans.CustomNumberEditor;
import com.minis.ioc.beans.PropertyEditor;
import com.minis.utils.StringUtils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/4/6 20:25 
 */
public class CustomDateEditor implements PropertyEditor {
    private Class<Date> dateClass;
    private DateTimeFormatter dateTimeFormatter;
    private boolean allowEmpty;
    private Date value;

    public CustomDateEditor() throws IllegalArgumentException{
        this(Date.class, "yyyy-MM-dd", true);
    }
    public CustomDateEditor(Class<Date> dateClass) throws  IllegalArgumentException{
        this(Date.class, "yyyy-MM-dd", true);
    }
    public CustomDateEditor(Class<Date> dateClass, boolean allowEmpty) throws IllegalArgumentException{
        this(dateClass, "yyyy-MM-dd", allowEmpty);
    }
    public CustomDateEditor(Class<Date> dateClass, String pattern, boolean allowEmpty) throws IllegalArgumentException{
        this.dateClass = dateClass;
        this.dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        this.allowEmpty = allowEmpty;
    }

    @Override
    public void setAsText(String text) {
        if(this.allowEmpty && !StringUtils.hasText(text)){
            setValue(null);
        }
        else {
            LocalDate localDate = LocalDate.parse(text, dateTimeFormatter);
            setValue(Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }
    }

    @Override
    public void setValue(Object value) {
        this.value = (Date) value;
    }

    @Override
    public Object getValue() {
        return this.value;
    }

    @Override
    public String getAsText() {
        Date value = this.value;
        if(value == null){
            return "";
        }
        else {
            LocalDate localDate = value.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            return localDate.format(dateTimeFormatter);
        }
    }
}
