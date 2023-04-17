package com.minis.web.converter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/4/7 00:44 
 */
public class DefaultHttpMessageConverter implements HttpMessageConverter{
    String defaultContentType = "text/json;charset=UTF-8";
    String defaultCharacterEncoding = "UTF-8";
    ObjectMapper objectMapper;

    public void setObjectMapper(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public void write(Object obj, HttpServletResponse response) throws IOException {
        response.setContentType(defaultContentType);
        response.setCharacterEncoding(defaultCharacterEncoding);
        writeInternal(obj, response); response.flushBuffer();
    }
    private void writeInternal(Object obj, HttpServletResponse response) throws IOException{
        String sJsonStr = this.objectMapper.writeValuesAsString(obj);
        PrintWriter pw = response.getWriter();
        pw.write(sJsonStr);
    }
}
