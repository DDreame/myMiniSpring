package com.minis.test.webTest;

import com.minis.test.CustomDateEditor;
import com.minis.web.databind.WebBindingInitializer;
import com.minis.web.databind.WebDataBinder;

import java.util.Date;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/4/6 20:41 
 */
public class DateInitializer implements WebBindingInitializer
{
    @Override
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(Date.class, "yyyy-MM-dd", false));
    }
}
