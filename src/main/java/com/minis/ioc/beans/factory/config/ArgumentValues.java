package com.minis.ioc.beans.factory.config;

import java.util.*;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/3/27 23:54 
 */
public class ArgumentValues {
    private final Map<Integer, ArgumentValue> indexedArgumentValue = new HashMap<>(0);
    private final List<ArgumentValue> genericArgumentValues = new ArrayList<>(0);

    private void addArgumentValue(Integer key, ArgumentValue argumentValue){
        this.indexedArgumentValue.put(key,argumentValue);
    }

    public boolean hasIndexedArgumentValue(Integer index){
        return this.indexedArgumentValue.containsKey(index);
    }

    public ArgumentValue getIndexedArgumentValue(Integer index){
        return this.indexedArgumentValue.get(index);
    }

    public void addGenericArgumentValue(Object value, String type){
        genericArgumentValues.add(new ArgumentValue(value, type));
    }

    public void addGenericArgumentValue(ArgumentValue newValue){
        if(newValue.getName() != null ){
            for(Iterator<ArgumentValue> iterator = genericArgumentValues.iterator(); iterator.hasNext();){
                ArgumentValue argumentValue = iterator.next();
                if(argumentValue.getName().equals(newValue.getName())){
                    iterator.remove();
                }
            }
        }
        addArgumentValue(this.genericArgumentValues.size(),newValue);
        this.genericArgumentValues.add(newValue);
    }

    public ArgumentValue getGenericArgumentValue(String requiredName){
        for(ArgumentValue argumentValue: this.genericArgumentValues){
            if(argumentValue.getName() != null && (!argumentValue.getName().equals(requiredName))){
                continue;
            }
            return argumentValue;
        }
        return null;
    }

    public int getArgumentCount(){
        return this.genericArgumentValues.size();
    }
    public boolean isEmpty() {
        return this.genericArgumentValues.isEmpty();
    }




}
