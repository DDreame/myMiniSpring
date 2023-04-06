package com.minis.ioc.beans;

import com.minis.ioc.beans.factory.config.PropertyValue;
import com.minis.ioc.beans.factory.config.PropertyValues;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/4/6 20:15 
 */
public abstract class AbstractPropertyAccessor extends PropertyEditorRegistrySupport{
    PropertyValues pvs;

    public AbstractPropertyAccessor() {
        super();

    }


    public void setPropertyValues(PropertyValues pvs) {
        this.pvs = pvs;
        for (PropertyValue pv : this.pvs.getPropertyValues()) {
            setPropertyValue(pv);
        }
    }

    public abstract void setPropertyValue(PropertyValue pv) ;
}
