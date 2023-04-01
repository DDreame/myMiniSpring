package com.minis.event;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/3/30 21:14 
 */
public class ContextRefreshEvent extends ApplicationEvent {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ContextRefreshEvent(Object source) {
        super(source);
    }

    public String toString() { return this.msg; }
}
