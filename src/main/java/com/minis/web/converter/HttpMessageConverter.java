package com.minis.web.converter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/4/7 00:43 
 */
public interface HttpMessageConverter {
    void write(Object obj, HttpServletResponse response) throws IOException;
}
