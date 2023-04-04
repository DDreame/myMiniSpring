package com.minis.ioc.event;

import java.util.EventObject;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/3/27 23:40 
 */
public class ApplicationEvent extends EventObject {

    private static final long serialVersionUID = 1L;

    protected String msg = null;
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationEvent(Object source) {

        super(source);
        this.msg = source.toString();
    }
}
