package com.minis.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/4/17 23:05 
 */
public interface View {
    void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response)      throws Exception;
    default String getContentType() {    return null;  }
    void setContentType(String contentType);
    void setUrl(String url);
    String getUrl();
    void setRequestContextAttribute(String requestContextAttribute);
    String getRequestContextAttribute();
}
