package com.minis.web;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/4/4 20:28 
 */
public class MappingValue {

    String url;

    String clz;

    String method;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getClz() {
        return clz;
    }

    public void setClz(String clz) {
        this.clz = clz;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public MappingValue(String url, String clz, String method) {
        this.url = url;
        this.clz = clz;
        this.method = method;
    }
}
